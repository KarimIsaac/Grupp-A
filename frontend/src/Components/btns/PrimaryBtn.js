import './PrimaryBtn.css'

export default function PrimaryBtn({text, clickFn}){
  return (
    <button className="primary-btn" onClick={clickFn}> {text} </button>
  )
}