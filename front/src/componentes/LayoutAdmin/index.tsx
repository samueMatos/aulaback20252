import { Outlet } from "react-router-dom";
import Footer from "../footer";
import Header from "../header";
import Sidebar from "../sidebar";

function LayoutAdmin(){
    return(
        <>
        <Header/>
            <div className="d-flex">
                <Sidebar/>
                <div className="flex-grow-1 p-4">
                    <Outlet/>
                </div>
            </div>
        <Footer/>
    </>

    );
}

export default LayoutAdmin;