import PrimaryBtn from "Components/btns/PrimaryBtn"
import './FormLayout.css'

export default function FormLayout({msg, redirectMsg, redirectFn, btnText, btnEventFn, children}){
  return (
  <div id="form-component">
    <div id="Input-container">
      {children}
    </div>  
    <div className="btn-container">
      <p>{msg} <i onClick={redirectFn}>{redirectMsg}</i> </p> 
      <PrimaryBtn text={btnText}  clickFn={btnEventFn}/>
    </div>
  </div>
  )
}