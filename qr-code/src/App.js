import "./style.css";
import QRCode from "qrcode.react";
import Qr from "./QR";

export default function App() {
  return (
    <Qr />
    // <div className="App">
    //   <div style={{ marginTop: 200, display: "flex", flexDirection: "row" }}>
    //     <div>
    //       <QRCode
    //         value="https://www.facebook.com"
    //         style={{ marginRight: 50 }}
    //       />
    //       <p>Tutorialspoint </p>
    //     </div>
    //     <div>
    //       <QRCode value="https://www.google.com/" style={{ marginRight: 50 }} />
    //       <p>google</p>
    //     </div>
    //     <div>
    //       <QRCode value="https://github.com/" style={{ marginRight: 50 }} />
    //       <p>github</p>
    //     </div>
    //     <div>
    //       <QRCode
    //         value="https://www.instagram.com/"
    //         style={{ marginRight: 50 }}
    //       />
    //       <p>instagram</p>
    //     </div>
    //     <div>
    //       <QRCode value="https://discord.com/" style={{ marginRight: 50 }} />
    //       <p>discord</p>
    //     </div>
    //   </div>
    // </div>
  );
}
let text = "Lakshya karan";
