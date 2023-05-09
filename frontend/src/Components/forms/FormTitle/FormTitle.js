import './FormTitle.css'

export default function FormTitle({text}) {
  return (
    <div className="form-title"> 
      <img src="shark.svg"/>
      <h2>{text} </h2>
    </div>
  )
}