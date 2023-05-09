import './FormInput.css'

export default function FormInput({inputId, pHolderText, getInputValue}) {

  function getValue (e){
    getInputValue(e.target.value)
    console.log(e.target.value) 
  }
  
  return (
    <div className="form-input">
      <label htmlFor={inputId}></label>
      <input 
        id={inputId} 
        placeholder={pHolderText} 
        onChange={getValue}
      />
    </div>
  )
}