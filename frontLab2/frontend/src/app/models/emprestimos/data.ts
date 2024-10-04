import { Emprestimo } from "./model";

export const contratos: Emprestimo[] = [
    {
        id: 1,
        valor_inicial: 1000,
        valor_total: 1200,
        juros: 200,
        placaCarro: 'ABC-1234',
        data_inicio: '2021-10-01',
        data_fim: '2021-10-31'
    },
    {
        id: 2,
        valor_inicial: 2000,
        valor_total: 2400,
        juros: 400,
        placaCarro: 'DEF-5678',
        data_inicio: '2021-11-01',
        data_fim: '2021-11-30'
    },
    {
        id: 3,
        valor_inicial: 3000,
        valor_total: 3600,
        juros: 600,
        placaCarro: 'GHI-9101',
        data_inicio: '2021-12-01',
        data_fim: '2021-12-31'
    },
    {
        id: 4,
        valor_inicial: 4000,
        valor_total: 4800,
        juros: 800,
        placaCarro: 'JKL-1213',
        data_inicio: '2022-01-01',
        data_fim: '2022-01-31'
    },
    {
        id: 5,
        valor_inicial: 5000,
        valor_total: 6000,
        juros: 1000,
        placaCarro: 'MNO-1415',
        data_inicio: '2022-02-01',
        data_fim: '2022-02-28'
    },
    {
        id: 6,
        valor_inicial: 6000,
        valor_total: 7200,
        juros: 1200,
        placaCarro: 'PQR-1617',
        data_inicio: '2022-03-01',
        data_fim: '2022-03-31'
    }
];
