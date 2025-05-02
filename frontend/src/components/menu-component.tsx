import { BellIcon, CreditCard, Home, User, Wallet } from "lucide-react";
import { Button } from "./ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "./ui/dropdown-menu";
import { Link, useLocation } from "react-router";

type AppRoutesType = '/'
  | '/transacoes'

const MenuComponent = () => {

  const location = useLocation()

  const getLinkClassName = (path: AppRoutesType) => {
    const isActive = location.pathname === path;
    return `flex items-center gap-2 rounded-md px-3 py-2 ${
      isActive
        ? "text-zinc-900 font-medium bg-zinc-100"
        : "text-zinc-600 hover:text-zinc-900 hover:bg-zinc-100"
    }`;
  };

  return (
    <>
      <header className="sticky top-0 z-10 flex h-16 items-center border-b bg-white px-6 shadow-sm">
        <div className="flex items-center gap-2">
          <Wallet className="h-6 w-6 text-zinc-800" />
          <span className="text-lg font-semibold text-zinc-900">
            Controle Financeiro
          </span>
        </div>

        <nav className="hidden md:flex items-center ml-10 space-x-1">
          <Link
            to="/"
            className={getLinkClassName("/")}
          >
            <Home className="h-4 w-4" />
            Início
          </Link>
          <Link
            to="/transacoes"
            className={getLinkClassName("/transacoes")}
          >
            <CreditCard className="h-4 w-4" />
            Transações
          </Link>
          {/* 
          <a
            href="#"
            className="flex items-center gap-2 rounded-md px-3 py-2 text-zinc-600 hover:text-zinc-900 hover:bg-zinc-100"
          >
            <LineChart className="h-4 w-4" />
            Relatórios
          </a>
          <a
            href="#"
            className="flex items-center gap-2 rounded-md px-3 py-2 text-zinc-600 hover:text-zinc-900 hover:bg-zinc-100"
          >
            <Settings className="h-4 w-4" />
            Configurações
          </a> */}
        </nav>

        <div className="ml-auto flex items-center gap-4">
          <Button
            variant="ghost"
            size="icon"
            className="text-zinc-500 hover:text-zinc-900"
          >
            <BellIcon className="h-5 w-5" />
            <span className="sr-only">Notificações</span>
          </Button>
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button
                variant="ghost"
                size="icon"
                className="rounded-full h-8 w-8 bg-zinc-100 text-zinc-700"
              >
                <User className="h-4 w-4" />
                <span className="sr-only">Perfil</span>
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuLabel>Minha Conta</DropdownMenuLabel>
              <DropdownMenuSeparator />
              <DropdownMenuItem>Perfil</DropdownMenuItem>
              <DropdownMenuSeparator />
              <DropdownMenuItem className="text-red-500">Sair</DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      </header>
    </>
  );
};

export { MenuComponent };
