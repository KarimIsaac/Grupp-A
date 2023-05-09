import './SecondaryBtn.css'
export default function SecondaryBtn({text, clickFn}){
  return (
    <button className="secondary-btn" onClick={clickFn}> {text} </button>
  )
}