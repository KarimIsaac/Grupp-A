import './PrimaryCardLayout.css'

export default function PrimaryCardLayout ({children}){
  return (
    <section id="primary-card">
      <div id="img-container">
        <img src="DeepOcean.png"/>
      </div>
      <div id="white-panel">
      </div>
      <div id="current-form-container">
        {children}
      </div>
    </section>
  )
}