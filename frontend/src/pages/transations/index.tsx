import { NewTransationDialog } from "@/components/transations/new-transation-dialog";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { ArrowDown, ArrowUp, Filter, Plus, Search } from "lucide-react";
import { useState } from "react";

// Dados de exemplo para transações
const transacoes = [
  {
    id: 1,
    tipo: "entrada",
    descricao: "Salário",
    categoria: "Salário",
    data: "15/04/2023",
    valor: "4.250,00",
  },
  {
    id: 2,
    tipo: "saida",
    descricao: "Supermercado Extra",
    categoria: "Alimentação",
    data: "18/04/2023",
    valor: "235,42",
  },
  {
    id: 3,
    tipo: "saida",
    descricao: "Restaurante Sabor Brasil",
    categoria: "Alimentação",
    data: "20/04/2023",
    valor: "89,90",
  },
  {
    id: 4,
    tipo: "saida",
    descricao: "Farmácia São Paulo",
    categoria: "Saúde",
    data: "22/04/2023",
    valor: "45,67",
  },
  {
    id: 5,
    tipo: "saida",
    descricao: "Posto Ipiranga",
    categoria: "Transporte",
    data: "23/04/2023",
    valor: "150,00",
  },
  {
    id: 6,
    tipo: "entrada",
    descricao: "Reembolso Empresa",
    categoria: "Reembolso",
    data: "25/04/2023",
    valor: "120,00",
  },
];

const Transation = () => {
  const [isOpen, setIsOpen] = useState(false)
  return (
    <main className="flex-1 p-6 bg-zinc-50">
      <div className="mb-8 flex items-center justify-between">
        <section>
          <h1 className="text-2xl font-bold text-zinc-900 mb-1">
            Transações de Lucas Davila
          </h1>
          <p className="text-zinc-500">
            Consulte e realize solicitações de gastos
          </p>
        </section>

        <Button size="sm" className="gap-1 bg-zinc-800 hover:bg-zinc-900" onClick={() => setIsOpen(true)}>
          <Plus className="h-4 w-4" /> Nova Transação
        </Button>
      </div>

      <div className="mb-6 flex flex-col gap-4 md:flex-row md:items-center md:justify-between">
        <div className="relative w-full max-w-sm">
          <Search className="absolute left-3 top-2.5 h-4 w-4 text-zinc-500" />
          <Input
            className="pl-9 border-zinc-200"
            placeholder="Buscar transações..."
          />
        </div>

        <div className="flex items-center gap-2">
          <Button variant="outline" size="sm" className="gap-1 border-zinc-200">
            <Filter className="h-4 w-4" /> Filtrar
          </Button>
        </div>
      </div>

      <Card className="bg-white shadow-sm">
        <CardContent className="p-0">
          <div className="divide-y">
            {transacoes.map((t) => (
              <div
                key={t.id}
                className="flex items-center gap-4 p-4 hover:bg-zinc-50"
              >
                <div
                  className={`rounded-full p-2 ${
                    t.tipo === "entrada"
                      ? "bg-green-50 text-green-600"
                      : "bg-red-50 text-red-500"
                  }`}
                >
                  {t.tipo === "entrada" ? (
                    <ArrowUp className="h-4 w-4" />
                  ) : (
                    <ArrowDown className="h-4 w-4" />
                  )}
                </div>
                <div className="flex-1">
                  <p className="text-sm font-medium text-zinc-800">
                    {t.descricao}
                  </p>
                  <div className="flex items-center gap-2">
                    <span className="text-xs text-zinc-500">{t.categoria}</span>
                    <span className="text-xs text-zinc-400">.</span>
                    <span className="text-xs text-zinc-500">{t.data}</span>
                  </div>
                </div>

                <div
                  className={`text-sm font-medium ${
                    t.tipo === "entrada" ? "text-green-600" : "text-red-500"
                  }`}
                >
                  {t.tipo === "entrada" ? "+" : "-"} R$ {t.valor}
                </div>
              </div>
            ))}
          </div>
        </CardContent>
      </Card>

      <div className="mt-6 grid gap-6 md:grid-cols-3">
        <Card className="bg-white shadow-sm">
          <CardHeader>
            <CardTitle className="text-sm font-medium text-zinc-500">
              Total de Entradas
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold text-zinc-900">R$ 4.370,00</div>
            <p className="text-xs text-green-600 mt-1 flex items-center">
              <ArrowUp className="h-3 w-3 mr-1" />
              12% este mês
            </p>
          </CardContent>
        </Card>

        <Card className="bg-white shadow-sm">
          <CardHeader>
            <CardTitle className="text-sm font-medium text-zinc-500">
              Total de Saídas
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold text-zinc-900">R$ 520,99</div>
            <p className="text-xs text-red-500 mt-1 flex items-center">
              <ArrowDown className="h-3 w-3 mr-1" />
              8% este mês
            </p>
          </CardContent>
        </Card>

        <Card className="bg-white shadow-sm">
          <CardHeader>
            <CardTitle className="text-sm font-medium text-zinc-500">
              Saldo
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold text-zinc-900">R$ 3.849,01</div>
            <p className="text-xs text-green-600 mt-1 flex items-center">
              <ArrowUp className="h-3 w-3 mr-1" />
              15% este mês
            </p>
          </CardContent>
        </Card>
      </div>

      <NewTransationDialog open={isOpen} onOpenChange={setIsOpen} />
    </main>
  );
};

export { Transation };
