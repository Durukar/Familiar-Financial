import { MenuComponent } from "@/components/menu-component";
import { Outlet } from "react-router";

const AppLayout = () => {
  return (
    <div className="flex min-h-screen flex-col bg-white">
      <MenuComponent />
      <Outlet />
    </div>
  );
};

export { AppLayout };
