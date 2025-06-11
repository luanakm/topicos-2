'use client';

import axios from 'axios';
import {
  addDays,
  addWeeks,
  format,
  isToday,
  startOfWeek,
  subWeeks,
} from 'date-fns';
import { ptBR } from 'date-fns/locale';
import React, { useEffect, useState } from 'react';

type Agendamento = {
  id: number;
  nome: string;
  servico: string;
  dataHora: string;
  funcionarioId: number;
  funcionarioNome: string;
};

type Funcionario = {
  id: number;
  nome: string;
};

export default function Calendario() {
  const [semanaAtual, setSemanaAtual] = useState(new Date());
  const [mostrarModal, setMostrarModal] = useState(false);
  const [agendamentos, setAgendamentos] = useState<Agendamento[]>([]);
  const [dadosFormulario, setDadosFormulario] = useState({
    id: 0,
    nome: '',
    servico: '',
    dataHora: '',
    funcionarioId: 0,
  });

  const [mostrarModalFuncionario, setMostrarModalFuncionario] = useState(false);
  const [nomeFuncionario, setNomeFuncionario] = useState('');
  const [funcionarios, setFuncionarios] = useState<{ id: number; nome: string }[]>([]);




  async function carregarAgendamentos() {
    try {
      const response = await axios.get(process.env.NEXT_PUBLIC_API_URL + '/agenda');
      setAgendamentos(response.data);
    } catch (error) {
      console.error('Erro ao buscar agendamentos:', error);
      setAgendamentos([]);
    }
  }

  async function carregarFuncionarios() {
    try {
      const response = await axios.get(process.env.NEXT_PUBLIC_API_URL + '/funcionario');
      setFuncionarios(response.data);
    } catch (error) {
      console.error('Erro ao buscar funcionários:', error);
      setFuncionarios([]);
    }
  }

  useEffect(() => {
    carregarAgendamentos();
    carregarFuncionarios();
  }, []);

  const diasDaSemana: Date[] = [];
  for (let i = 0; i < 7; i++) {
    diasDaSemana.push(addDays(startOfWeek(semanaAtual, { weekStartsOn: 1 }), i));
  }

  const horas = [];
  for (let i = 8; i <= 18; i++) {
    horas.push(i + 'h');
  }

  function irParaSemanaAnterior() {
    setSemanaAtual(subWeeks(semanaAtual, 1));
  }

  function irParaProximaSemana() {
    setSemanaAtual(addWeeks(semanaAtual, 1));
  }

  function irParaHoje() {
    setSemanaAtual(new Date());
  }

  function lidarComMudanca(e: any) {
    setDadosFormulario({ ...dadosFormulario, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    try {
      const payload = {
        nome: dadosFormulario.nome,
        servico: dadosFormulario.servico,
        dataHora: dadosFormulario.dataHora,
        funcionarioId: Number(dadosFormulario.funcionarioId),
      };

      if (dadosFormulario.id === 0) {
        await axios.post(process.env.NEXT_PUBLIC_API_URL + '/agenda', payload);
      } else {
        await axios.put(
          process.env.NEXT_PUBLIC_API_URL + '/agenda/' + dadosFormulario.id,
          payload
        );
      }

      setMostrarModal(false);
      setDadosFormulario({ id: 0, nome: '', servico: '', dataHora: '', funcionarioId: 0 });
      await carregarAgendamentos();
    } catch (error) {
      console.error('Erro ao salvar:', error);
      alert('Erro ao salvar. Tente novamente.');
    }
  }

  async function handleSalvarFuncionario(e: React.FormEvent) {
    e.preventDefault();
    try {
      await axios.post(process.env.NEXT_PUBLIC_API_URL + '/funcionario', {
        nome: nomeFuncionario,
      });
      setMostrarModalFuncionario(false);
      setNomeFuncionario('');
      await carregarFuncionarios(); // atualiza a lista de funcionários
    } catch (err) {
      console.error('Erro ao cadastrar funcionário:', err);
      alert('Erro ao salvar funcionário.');
    }
  }


  function editarAgendamento(agendamento: Agendamento) {
    setDadosFormulario({
      id: agendamento.id,
      nome: agendamento.nome,
      servico: agendamento.servico,
      dataHora: agendamento.dataHora,
      funcionarioId: agendamento.funcionarioId,
    });
    setMostrarModal(true);
  }

  async function deletarRegistro(id: number) {
    try {
      await axios.delete(process.env.NEXT_PUBLIC_API_URL + '/agenda/' + id);
      await carregarAgendamentos();
      setMostrarModal(false);
    } catch (error) {
      console.error('Erro ao deletar:', error);
      alert('Erro ao deletar. Tente novamente.');
    }
  }

  function corPorServico(servico: string) {
    if (servico === 'banho') return 'bg-blue-100 text-blue-700';
    if (servico === 'tosa') return 'bg-green-100 text-green-700';
    if (servico === 'banho+tosa') return 'bg-pink-100 text-pink-700';
    return 'bg-gray-100 text-gray-700';
  }

  return (
    <div className="p-6 max-w-7xl mx-auto bg-[#f9fafb] min-h-screen rounded-xl">
      {/* Cabeçalho */}
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-bold text-gray-800">
          {format(semanaAtual, 'MMMM yyyy', { locale: ptBR })}
        </h2>
        <div className="flex items-center gap-2">
          <button onClick={irParaSemanaAnterior} className="text-gray-500 hover:text-gray-700">←</button>
          <button onClick={irParaHoje} className="px-3 py-1 border border-gray-300 rounded hover:bg-gray-100 text-sm">Hoje</button>
          <button onClick={irParaProximaSemana} className="text-gray-500 hover:text-gray-700">→</button>
          <button
            onClick={() => setMostrarModal(true)}
            className="ml-4 bg-violet-600 text-white px-4 py-2 rounded-lg hover:bg-violet-700 transition"
          >
            Novo Agendamento
          </button>
          <button
            onClick={() => setMostrarModalFuncionario(true)}
            className="bg-emerald-600 text-white px-4 py-2 rounded-lg hover:bg-emerald-700 transition"
          >
            Novo Funcionário
          </button>

        </div>
      </div>

      {/* Tabela da semana */}
      <div className="grid grid-cols-8 border-t border-l rounded-xl overflow-hidden border-gray-300 bg-white">
        <div className="border-b border-r border-gray-300 h-12 bg-gray-50"></div>
        {diasDaSemana.map((dia, indice) => (
          <div
            key={indice}
            className={`border-b border-r border-gray-300 h-12 text-center font-medium text-sm flex items-center justify-center ${isToday(dia) ? 'bg-violet-100 text-violet-700 font-semibold' : 'bg-gray-50 text-gray-600'
              }`}
          >
            {format(dia, 'EEE dd', { locale: ptBR })}
          </div>
        ))}

        {horas.map((hora, indexHora) => (
          <React.Fragment key={indexHora}>
            <div className="border-b border-r border-gray-300 h-16 text-xs text-gray-500 flex items-center justify-center bg-gray-50">
              {hora}
            </div>
            {diasDaSemana.map((dia, indexDia) => {
              const dataFormatada = format(dia, 'yyyy-MM-dd');
              const horaAtual = (indexHora + 8).toString().padStart(2, '0') + ':00';
              const agendamento = agendamentos.find((ag) => {
                const [dataAg, horaAg] = ag.dataHora.split('T');
                return dataAg === dataFormatada && horaAg.startsWith(horaAtual);
              });

              return (
                <div
                  key={indexDia}
                  className={`border-b border-r border-gray-300 h-16 px-1 text-xs hover:bg-violet-50 cursor-pointer transition flex items-center justify-center ${agendamento ? corPorServico(agendamento.servico) : ''
                    }`}
                  onClick={() => agendamento && editarAgendamento(agendamento)}
                >
                  {agendamento ? (
                    <div className="text-center">
                      <div className="font-semibold text-violet-700">{agendamento.nome}</div>
                      <div className="text-gray-500 text-xs">{agendamento.servico}</div>
                      <div className="text-gray-400 text-[10px] italic">{agendamento.funcionarioNome}</div>
                    </div>
                  ) : null}
                </div>
              );
            })}
          </React.Fragment>
        ))}
      </div>

      {/* Modal */}
      {mostrarModal && (
        <div className="fixed inset-0 bg-gray-200 bg-opacity-10 backdrop-blur-none flex justify-center items-center z-50">
          <div className="bg-white rounded-xl p-6 w-full max-w-md shadow-md">
            <h2 className="text-xl font-semibold mb-4 text-gray-800">Novo Agendamento</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700">Nome do pet</label>
                <input
                  type="text"
                  name="nome"
                  value={dadosFormulario.nome}
                  onChange={lidarComMudanca}
                  className="mt-1 w-full border border-gray-300 rounded-lg px-3 py-2"
                  required
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700">Serviço</label>
                <select
                  name="servico"
                  value={dadosFormulario.servico}
                  onChange={lidarComMudanca}
                  className="mt-1 w-full border border-gray-300 rounded-lg px-3 py-2"
                  required
                >
                  <option value="">Selecione</option>
                  <option value="banho">Banho</option>
                  <option value="tosa">Tosa</option>
                  <option value="banho+tosa">Banho + Tosa</option>
                </select>
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700">Funcionário</label>
                <select
                  name="funcionarioId"
                  value={dadosFormulario.funcionarioId}
                  onChange={lidarComMudanca}
                  className="mt-1 w-full border border-gray-300 rounded-lg px-3 py-2"
                  required
                >
                  <option value="">Selecione um funcionário</option>
                  {funcionarios.map((f) => (
                    <option key={f.id} value={f.id}>
                      {f.nome}
                    </option>
                  ))}
                </select>
              </div>

              <div className="grid grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">Data</label>
                  <input
                    type="date"
                    value={dadosFormulario.dataHora.split('T')[0] || ''}
                    onChange={(e) => {
                      const hora = dadosFormulario.dataHora.split('T')[1] || '';
                      setDadosFormulario({ ...dadosFormulario, dataHora: `${e.target.value}T${hora}` });
                    }}
                    className="mt-1 w-full border border-gray-300 rounded px-3 py-2"
                    required
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Hora</label>
                  <select
                    value={dadosFormulario.dataHora.split('T')[1] || ''}
                    onChange={(e) => {
                      const data = dadosFormulario.dataHora.split('T')[0] || '';
                      setDadosFormulario({ ...dadosFormulario, dataHora: `${data}T${e.target.value}` });
                    }}
                    className="mt-1 w-full border border-gray-300 rounded px-3 py-2"
                    required
                  >
                    <option value="">Selecione...</option>
                    {Array.from({ length: 11 }, (_, i) => {
                      const hora = i + 8;
                      return <option key={hora} value={`${hora}:00`}>{`${hora}:00`}</option>;
                    })}
                  </select>
                </div>
              </div>

              <div className="flex justify-between items-center mt-4">
                <button
                  type="button"
                  onClick={() => {
                    setMostrarModal(false);
                    setDadosFormulario({ id: 0, nome: '', servico: '', dataHora: '', funcionarioId: 0 });
                  }}
                  className="px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-100"
                >
                  Cancelar
                </button>

                <div className="flex gap-2">
                  {dadosFormulario.id !== 0 && (
                    <button
                      type="button"
                      onClick={() => deletarRegistro(dadosFormulario.id)}
                      className="px-4 py-2 bg-red-400 text-white rounded-lg hover:bg-red-500 transition"
                    >
                      Deletar
                    </button>
                  )}
                  <button
                    type="submit"
                    className="px-4 py-2 bg-violet-600 text-white rounded-lg hover:bg-violet-700 transition"
                  >
                    Salvar
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      )}
      {mostrarModalFuncionario && (
        <div className="fixed inset-0 bg-gray-200 bg-opacity-10 backdrop-blur-none flex justify-center items-center z-50">
          <div className="bg-white rounded-xl p-6 w-full max-w-md shadow-md">
            <h2 className="text-xl font-semibold mb-4 text-gray-800">Novo Funcionário</h2>
            <form onSubmit={handleSalvarFuncionario} className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700">Nome</label>
                <input
                  type="text"
                  value={nomeFuncionario}
                  onChange={(e) => setNomeFuncionario(e.target.value)}
                  className="mt-1 w-full border border-gray-300 rounded-lg px-3 py-2"
                  required
                />
              </div>

              <div className="flex justify-between items-center mt-4">
                <button
                  type="button"
                  onClick={() => {
                    setMostrarModalFuncionario(false);
                    setNomeFuncionario('');
                  }}
                  className="px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-100"
                >
                  Cancelar
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-violet-600 text-white rounded-lg hover:bg-violet-700 transition"
                >
                  Salvar
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

    </div>
  );
}
