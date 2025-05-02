import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useOnce } from "@/hooks/useOnce";
import { useAuthStore } from "@/stores/useAuthStore";
import { CreditCard, DollarSign } from "lucide-react";

const Home = () => {

  const {login} = useAuthStore()

  const useLogin = async () => {
    await login('suporte', 'ff123')
  }

  useOnce(useLogin)

  return (
    <main className="flex-1 p-6">
      <div className="mb-8">
        <h1 className="text-2xl font-bold text-zinc-900 mb-1">Olá, Lucas D.</h1>
        <p className="text-zinc-500">Bem-vindo ao seu painel financeiro</p>
      </div>

      <div className="grid gap-6 md:grid-cols-3">
        <Card className="bg-gradient-to-br from-zinc-800 to-zinc-900 text-white shadow-lg shadow-zinc-200">
          <CardHeader className="pb-2">
            <CardTitle className="text-sm font-medium text-zinc-300">
              Saldo Total
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="text-3xl font-bold">R$ 12.580,45</div>
            <p className="text-xs text-zinc-300 mt-1 flex items-center">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                className="h-3 w-3 mr-1"
              >
                <path d="M12 19V5M18 13l-6 6M6 13l6 6" />
              </svg>
              2,5% este mês
            </p>
          </CardContent>
        </Card>

        <Card className="bg-white shadow-sm">
          <CardHeader className="pb-2">
            <CardTitle className="text-sm font-medium text-zinc-500">
              Receitas
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold text-zinc-900">R$ 4.250,00</div>
            <p className="text-xs text-green-600 mt-1 flex items-center">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                className="h-3 w-3 mr-1"
              >
                <path d="M12 5v14M18 11l-6-6M6 11l6-6" />
              </svg>
              12% este mês
            </p>
          </CardContent>
        </Card>

        <Card className="bg-white shadow-sm">
          <CardHeader className="pb-2">
            <CardTitle className="text-sm font-medium text-zinc-500">
              Despesas
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold text-zinc-900">R$ 2.150,30</div>
            <p className="text-xs text-red-500 mt-1 flex items-center">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                className="h-3 w-3 mr-1"
              >
                <path d="M12 19V5M18 13l-6 6M6 13l6 6" />
              </svg>
              8% este mês
            </p>
          </CardContent>
        </Card>
      </div>

      <div className="mt-8">
        <h2 className="text-lg font-bold text-zinc-800 mb-4">
          Transações Recentes
        </h2>
        <Card className="bg-white shadow-sm">
          <CardContent className="p-0">
            <div className="divide-y">
              <div className="flex items-center gap-4 p-4">
                <div className="rounded-full bg-red-50 p-2 text-red-500">
                  <CreditCard className="h-4 w-4" />
                </div>
                <div className="flex-1">
                  <p className="text-sm font-medium text-zinc-800">
                    Supermercado Extra
                  </p>
                  <p className="text-xs text-zinc-500">Hoje, 14:30</p>
                </div>
                <div className="text-sm font-medium text-red-500">
                  - R$ 235,42
                </div>
              </div>

              <div className="flex items-center gap-4 p-4">
                <div className="rounded-full bg-green-50 p-2 text-green-600">
                  <DollarSign className="h-4 w-4" />
                </div>
                <div className="flex-1">
                  <p className="text-sm font-medium text-zinc-800">Salário</p>
                  <p className="text-xs text-zinc-500">Ontem, 08:15</p>
                </div>
                <div className="text-sm font-medium text-green-600">
                  + R$ 4.250,00
                </div>
              </div>

              <div className="flex items-center gap-4 p-4">
                <div className="rounded-full bg-red-50 p-2 text-red-500">
                  <CreditCard className="h-4 w-4" />
                </div>
                <div className="flex-1">
                  <p className="text-sm font-medium text-zinc-800">
                    Restaurante Sabor Brasil
                  </p>
                  <p className="text-xs text-zinc-500">Ontem, 20:45</p>
                </div>
                <div className="text-sm font-medium text-red-500">
                  - R$ 89,90
                </div>
              </div>
            </div>
          </CardContent>
        </Card>
      </div>
    </main>
  );
};

export { Home };
