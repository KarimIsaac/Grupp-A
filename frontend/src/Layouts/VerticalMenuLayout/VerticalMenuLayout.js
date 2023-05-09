import "./VerticalMenuLayout.css";

export default function VerticalMenuLayout({userName, children}) {
  return (
    <section id="vertical-menu">
      <div className="header-container">
        <h2>{userName}</h2>
      </div>
      <div id="btn-container">
        {children}
      </div>
    </section>
  )
}