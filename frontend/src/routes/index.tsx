import { AppLayout } from "@/layouts/app-layout";
import { Home } from "@/pages/home";
import { Transation } from "@/pages/transations";
import { BrowserRouter, Route, Routes } from "react-router";

const RouterApp = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/">
          <Route element={<AppLayout />}>
            <Route index element={<Home />} />
            <Route path="/transacoes" element={<Transation />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export { RouterApp };
