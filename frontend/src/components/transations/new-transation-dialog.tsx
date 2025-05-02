import { DialogDescription } from "@radix-ui/react-dialog";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "../ui/dialog";
import { Label } from "../ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import { useState } from "react";
import { Popover, PopoverContent, PopoverTrigger } from "../ui/popover";
import { Button } from "../ui/button";
import { Calendar, Check, ChevronsUpDown, DollarSign } from "lucide-react";
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from "../ui/command";
import { cn } from "@/lib/utils";
import { Input } from "../ui/input";
import { Textarea } from "../ui/textarea";
import { Card, CardContent } from "../ui/card";

// Datasources

const tiposTransacao = [
  { value: "deposito", label: "Depósito" },
  { value: "transferencia", label: "Transferência" },
  { value: "pagamento", label: "Pagamento" },
  { value: "saque", label: "Saque" },
];

const contas = [
  { value: "conta-corrente", label: "Conta Corrente", saldo: "R$ 8.350,75" },
  { value: "conta-poupanca", label: "Conta Poupança", saldo: "R$ 4.229,70" },
];

const categorias = [
  { value: "salario", label: "Salário" },
  { value: "investimento", label: "Investimento" },
  { value: "transferencia", label: "Transferência" },
  { value: "reembolso", label: "Reembolso" },
  { value: "outros", label: "Outros" },
];

interface NewTransationDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
}

const NewTransationDialog = ({
  open,
  onOpenChange,
}: NewTransationDialogProps) => {
  const [tipoTransacao, setTipoTransacao] = useState("deposito");
  const [contaSelecionada, setContaSelecionada] = useState("");
  const [categoriaSelecionada, setCategoriaSelecionada] = useState("");
  const [valor, setValor] = useState("");
  const [data, setData] = useState("");
  const [descricao, setDescricao] = useState("");
  const [openConta, setOpenConta] = useState(false);
  const [openCategoria, setOpenCategoria] = useState(false);
  const [mostrarResumo, setMostrarResumo] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    console.log({
      tipoTransacao,
      contaSelecionada,
      categoriaSelecionada,
      valor,
      data,
      descricao,
    });

    resetForm();
    onOpenChange(false);
  };

  const resetForm = () => {
    setTipoTransacao("deposito");
    setContaSelecionada("");
    setCategoriaSelecionada("");
    setValor("");
    setData("");
    setDescricao("");
    setMostrarResumo(false);
  };

  const handleValorChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValor(e.target.value);

    if (e.target.value) {
      setMostrarResumo(true);
    } else {
      setMostrarResumo(false);
    }
  };

  const handleCancel = () => {
    resetForm();
    onOpenChange(false);
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="sm:max-w-[600px] max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle className="text-xl text-zinc-900">
            Registrar Transação
          </DialogTitle>
          <DialogDescription className="text-zinc-500">
            Preencha os detalhes para registrar uma nova transação na sua conta.
          </DialogDescription>
        </DialogHeader>

        {/* Formulario de transação */}
        <form onSubmit={handleSubmit} className="space-y-6 py-4">
          {/* Tipo da transação */}
          <div className="space-y-2">
            <Label className="text-sm font-medium text-zinc-700">
              Tipo da Transação
            </Label>
            <Select value={tipoTransacao} onValueChange={setTipoTransacao}>
              <SelectTrigger className="w-full border-zinc-200">
                <SelectValue placeholder="Selecione o tipo" />
              </SelectTrigger>
              <SelectContent>
                {tiposTransacao.map((t) => (
                  <SelectItem key={t.value} value={t.value}>
                    {t.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          {/* Conta */}
          <div className="space-y-2">
            <Label className="text-sm font-medium text-zinc-700">Conta</Label>
            <Popover open={openConta} onOpenChange={setOpenConta}>
              <PopoverTrigger asChild>
                <Button
                  variant="outline"
                  role="combobox"
                  aria-expanded={openConta}
                  className="w-full justify-between border-zinc-200 font-normal"
                >
                  {contaSelecionada
                    ? contas.find((conta) => conta.value === contaSelecionada)
                        ?.label
                    : "Selecione a conta"}
                  <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-full p-0">
                <Command>
                  <CommandInput placeholder="Buscar conta..." />
                  <CommandList>
                    <CommandEmpty>Nenhuma conta encontrada.</CommandEmpty>
                    <CommandGroup>
                      {contas.map((conta) => (
                        <CommandItem
                          key={conta.value}
                          value={conta.value}
                          onSelect={(currentValue) => {
                            setContaSelecionada(
                              currentValue === contaSelecionada
                                ? ""
                                : currentValue,
                            );
                            setOpenConta(false);
                          }}
                        >
                          <Check
                            className={cn(
                              "mr-2 h-4 w-4",
                              contaSelecionada === conta.value
                                ? "opacity-100"
                                : "opacity-0",
                            )}
                          />
                          <div className="flex flex-1 items-center justify-between">
                            <span>{conta.label}</span>
                            <span className="text-sm text-zinc-500">
                              {conta.saldo}
                            </span>
                          </div>
                        </CommandItem>
                      ))}
                    </CommandGroup>
                  </CommandList>
                </Command>
              </PopoverContent>
            </Popover>
          </div>

          {/* Valor */}
          <div className="space-y-2">
            <Label className="text-sm font-medium text-zinc-700">Valor</Label>
            <div className="relative">
              <DollarSign className="absolute left-3 top-2.5 h-5 w-5 text-zinc-500" />
              <Input
                type="text"
                placeholder="0,00"
                className="pl-10 border-zinc-200"
                value={valor}
                onChange={handleValorChange}
              />
            </div>
          </div>

          {/* Data */}
          <div className="space-y-2">
            <Label className="text-sm font-medium text-zinc-700">Data</Label>
            <div className="relative">
              <Calendar className="absolute left-3 top-2.5 h-5 w-5 text-zinc-500" />
              <Input
                type="date"
                className="pl-10 border-zinc-200"
                value={data}
                onChange={(e) => setData(e.target.value)}
              />
            </div>
          </div>

          {/* Categoria */}
          <div className="space-y-2">
            <Label className="text-sm font-medium text-zinc-700">
              Categoria
            </Label>
            <Popover open={openCategoria} onOpenChange={setOpenCategoria}>
              <PopoverTrigger asChild>
                <Button
                  variant="outline"
                  role="combobox"
                  aria-expanded={openCategoria}
                  className="w-full justify-between border-zinc-200 font-normal"
                >
                  {categoriaSelecionada
                    ? categorias.find(
                        (categoria) => categoria.value === categoriaSelecionada,
                      )?.label
                    : "Selecione a categoria"}
                  <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-full p-0">
                <Command>
                  <CommandInput placeholder="Buscar categoria..." />
                  <CommandList>
                    <CommandEmpty>Nenhuma categoria encontrada.</CommandEmpty>
                    <CommandGroup>
                      {categorias.map((categoria) => (
                        <CommandItem
                          key={categoria.value}
                          value={categoria.value}
                          onSelect={(currentValue) => {
                            setCategoriaSelecionada(
                              currentValue === categoriaSelecionada
                                ? ""
                                : currentValue,
                            );
                            setOpenCategoria(false);
                          }}
                        >
                          <Check
                            className={cn(
                              "mr-2 h-4 w-4",
                              categoriaSelecionada === categoria.value
                                ? "opacity-100"
                                : "opacity-0",
                            )}
                          />
                          {categoria.label}
                        </CommandItem>
                      ))}
                    </CommandGroup>
                  </CommandList>
                </Command>
              </PopoverContent>
            </Popover>
          </div>

          {/* Descrição */}
          <div className="space-y-2">
            <Label className="text-sm font-medium text-zinc-700">
              Descrição
            </Label>
            <Textarea
              placeholder="Adicione detalhes sobre esta transação"
              className="min-h-[80px] border-zinc-200"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
            />
          </div>

          {/* Resumo da transação */}
          {mostrarResumo && (
            <Card className="bg-zinc-50 border-zinc-200">
              <CardContent className="pt-4">
                <h3 className="text-sm font-medium text-zinc-900 mb-3">
                  Resumo da Transação
                </h3>
                <div className="space-y-3 text-sm">
                  <div className="flex items-center justify-between border-b border-dashed border-zinc-200 pb-2">
                    <span className="text-zinc-500">Tipo</span>
                    <span className="font-medium text-zinc-800">
                      {tiposTransacao.find((t) => t.value === tipoTransacao)
                        ?.label || ""}
                    </span>
                  </div>

                  {contaSelecionada && (
                    <div className="flex items-center justify-between border-b border-dashed border-zinc-200 pb-2">
                      <span className="text-zinc-500">Conta</span>
                      <span className="font-medium text-zinc-800">
                        {contas.find((c) => c.value === contaSelecionada)
                          ?.label || ""}
                      </span>
                    </div>
                  )}

                  <div className="flex items-center justify-between border-b border-dashed border-zinc-200 pb-2">
                    <span className="text-zinc-500">Valor</span>
                    <span className="font-medium text-zinc-800">
                      {valor ? `R$ ${valor}` : ""}
                    </span>
                  </div>

                  {data && (
                    <div className="flex items-center justify-between border-b border-dashed border-zinc-200 pb-2">
                      <span className="text-zinc-500">Data</span>
                      <span className="font-medium text-zinc-800">{data}</span>
                    </div>
                  )}

                  {categoriaSelecionada && (
                    <div className="flex items-center justify-between pb-2">
                      <span className="text-zinc-500">Categoria</span>
                      <span className="font-medium text-zinc-800">
                        {categorias.find(
                          (c) => c.value === categoriaSelecionada,
                        )?.label || ""}
                      </span>
                    </div>
                  )}
                  
                </div>
              </CardContent>
            </Card>
          )}
        </form>

        <DialogFooter className="flex gap-2">
          <Button
            variant="outline"
            type="button"
            onClick={handleCancel}
            className="border-zinc-200 text-zinc-700"
          >
            Cancelar
          </Button>
          <Button
            type="submit"
            onClick={handleSubmit}
            className="bg-zinc-800 hover:bg-zinc-900"
          >
            Registrar Transação
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export { NewTransationDialog };
