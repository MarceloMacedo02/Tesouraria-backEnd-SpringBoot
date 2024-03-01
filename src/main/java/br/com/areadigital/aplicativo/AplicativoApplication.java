package br.com.areadigital.aplicativo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.areadigital.aplicativo.entities.Financeiro.ContaFinanceira;
import br.com.areadigital.aplicativo.entities.Financeiro.GrupoFinanceiro;
import br.com.areadigital.aplicativo.entities.enums.GrauSocio;
import br.com.areadigital.aplicativo.entities.enums.StatusMovimento;
import br.com.areadigital.aplicativo.entities.enums.TipoEntradaFicha;
import br.com.areadigital.aplicativo.entities.socio.FichaConta;
import br.com.areadigital.aplicativo.entities.socio.FichaSocio;
import br.com.areadigital.aplicativo.entities.socio.Socio;
import br.com.areadigital.aplicativo.entities.socio.SocioTesouraria;
import br.com.areadigital.aplicativo.repositories.FichaContaRepository;
import br.com.areadigital.aplicativo.repositories.FichaSocioRepository;
import br.com.areadigital.aplicativo.repositories.GrupoFinanceiroRepository;
import br.com.areadigital.aplicativo.repositories.SocioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class AplicativoApplication implements CommandLineRunner {

    private final GrupoFinanceiroRepository grupoFinanceiroRepository;
    private final SocioRepository socioRepository;
    private final FichaContaRepository fichaContaRepository;
    private final FichaSocioRepository fichaSocioRepository;

    public static void main(String[] args) {
        SpringApplication.run(AplicativoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<GrupoFinanceiro> grupos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GrupoFinanceiro grupo = new GrupoFinanceiro();
            grupo.setDescricao("Grupo " + (i + 1));
            grupo.setTipo("Entrada");
            grupos.add(grupo);
        }

        for (int i = 10; i < 20; i++) {
            GrupoFinanceiro grupo = new GrupoFinanceiro();
            grupo.setDescricao("Grupo " + (i + 1));

            grupo.setTipo("Saida");
            grupos.add(grupo);
        }

        List<ContaFinanceira> contas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ContaFinanceira conta = new ContaFinanceira();
            conta.setDescricao("Conta " + (i + 1));
            conta.setTipo("Entrada");
            conta.setGrupoFinanceiro(grupos.get(i));
            grupos.get(i).getContaFinanceira().add(conta);
            contas.add(conta);
        }

        for (int i = 10; i < 20; i++) {
            ContaFinanceira conta = new ContaFinanceira();
            conta.setGrupoFinanceiro(grupos.get(i));
            conta.setDescricao("Conta " + (i + 1));
            conta.setTipo("Saida");
            grupos.get(i).getContaFinanceira().add(conta);
            contas.add(conta);
        }

        grupos = grupoFinanceiroRepository.saveAll(grupos);

        List<FichaConta> fichas = new ArrayList<>();
        FichaConta fichaConta = new FichaConta();
        fichaConta.setContaFinanceira(contas.get(0));
        fichaConta.setDescricao(TipoEntradaFicha.MENSALIDADE.getName());
        fichaConta.setValor(1000.0);
        fichaContaRepository.save(fichaConta);
        fichas.add(fichaConta);

        FichaConta fichaContaOutrs = new FichaConta();
        fichaContaOutrs.setContaFinanceira(contas.get(1));
        fichaContaOutrs.setDescricao(TipoEntradaFicha.OUTRASENTRADAS.getName());
        fichaContaOutrs.setValor(10.0);
        fichaContaRepository.save(fichaContaOutrs);
        fichas.add(fichaContaOutrs);

        List<Socio> socios = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Socio socio = new Socio();
            socio.setEmail("email" + i + "@example.com");
            socio.setPassword("$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
            socio.setUsername("username" + i);
            socio.setNome("Nome" + i);
            socio.setGrauSocio(GrauSocio.DISCIPULO.name());
            socio.setAtivo(true);
            socio.setCargoDirtetoria("cargo" + i);
            socio.setIsDiretoria(false);
            socios.add(socio);

            SocioTesouraria socioTesouraria = new SocioTesouraria();
            socioTesouraria.setSocio(socio);
            socio.setSocioTesouraria(socioTesouraria);
            // socioTesouraria.setFichaSocios(processarFichasSocio(new ArrayList<>(),
            // fichaConta));
            socioTesouraria.setDependentes(new ArrayList<>());
            socioTesouraria.setFichaContas(List.of(fichaConta, fichaContaOutrs));
            // socioTesouraria.getFichaSocios().add(new FichaSocio());
            if (i > 1)
                socioTesouraria.getDependentes().add(socios.get(i - 1));

        }

        socios = socioRepository.saveAll(socios);
        for (Socio socio : socios) {
            socio.getSocioTesouraria()
                    .setFichaSocios(processarFichasSocio(new ArrayList<>(), fichaConta, socio.getDataInicial()));
            socioRepository.save(socio);
        }
    }

    public List<FichaSocio> processarFichasSocio(List<FichaSocio> fichasSocio, FichaConta fichaConta,
            Date dataInicial) {

        fichasSocio.removeIf(fichaSocio -> (StatusMovimento.ABERTO.getName().equals(fichaSocio.getStatusPagamento())
                && fichaSocio.getFichaConta().getDescricao().equals(TipoEntradaFicha.MENSALIDADE.getName())));

        // Encontrar o último mês em que foi registrado um pagamento quitado
        Calendar ultimoMesQuitado = Calendar.getInstance();
        ultimoMesQuitado.set(Calendar.MONTH, -1); // Inicializa com o mês anterior ao mês atual
        for (FichaSocio ficha : fichasSocio) {
            if (StatusMovimento.QUITADO.getName().equals(ficha.getStatusPagamento())
                    && ficha.getDataVencimento().after(ultimoMesQuitado.getTime())) {

                ultimoMesQuitado.setTime(ficha.getDataVencimento());
            }
        }
        Calendar dataIniciCalendar = Calendar.getInstance();
        dataIniciCalendar.setTime(dataInicial);
        if (ultimoMesQuitado.getTime().compareTo(dataIniciCalendar.getTime()) < 0) {
            ultimoMesQuitado = dataIniciCalendar;
        }
        // Avançar para o próximo mês após o último mês quitado
        ultimoMesQuitado.add(Calendar.MONTH, 1);

        // Definir o dia de vencimento como dia 10
        ultimoMesQuitado.set(Calendar.DAY_OF_MONTH, 10);

        // Criar novas instâncias com statusPagamento igual a "Quitado" e dataVencimento
        // a partir do próximo mês após o último mês quitado até dezembro do corrente
        // ano no dia 10
        Calendar calendar = ultimoMesQuitado;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        while (calendar.get(Calendar.YEAR) == currentYear && calendar.get(Calendar.MONTH) <= 11) {
            FichaSocio novaFichaSocio = new FichaSocio();
            novaFichaSocio.setStatusPagamento(StatusMovimento.ABERTO.name());
            novaFichaSocio.setFichaConta(new FichaConta());
            // novaFichaSocio.getFichaConta().setDescricao(fichaConta.getDescricao());
            // novaFichaSocio.getFichaConta().setValor(fichaConta.getValor());
            // novaFichaSocio.getFichaConta().setContaFinanceira(fichaConta.getContaFinanceira());
            novaFichaSocio.getFichaConta().setId(fichaConta.getId());
            novaFichaSocio.setDataVencimento(calendar.getTime());
            // novaFichaSocio.setFichaConta(fichaConta);
            // novaFichaSocio.setFichaConta(fichaConta);

            fichaSocioRepository.save(novaFichaSocio);
            fichasSocio.add(novaFichaSocio);

            calendar.add(Calendar.MONTH, 1); // Avançar para o próximo mês
        }
        return fichasSocio;
    }
}
