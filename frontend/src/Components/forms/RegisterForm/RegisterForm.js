import './RegisterForm.css'
import FormLayout from "Layouts/FormLayout/FormLayout";
import FormInput from "Components/forms/FormInput/FormInput";
import { useState } from "react";
import SharkParkLogo from "Components/SharkParkLogo/SharkParkLogo";

export default function RegisterForm({changeForm}){
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });
  
  function setName(data){
    setFormData((prev) => {
      return {...prev, name: data}
    })
  }

  function setEmail(data){
    setFormData(prev => {
      return {...prev, email: data}
    })  
  }

  function setPassword(data){
    setFormData(prev => {
      return {...prev, password: data}
    })  
  }

  return (
    <div id="register-form">
      <div id="SharkParkLogo">
        <SharkParkLogo />
      </div>
      <FormLayout msg="Already own an account?" redirectMsg="Login" redirectFn={changeForm} btnText="Sign up">
        <FormInput inputId="UserNameInput" pHolderText="Enter name..." getInputValue={setName} />
        <FormInput inputId="EmailInput" pHolderText="Enter email..." getInputValue={setEmail} />
        <FormInput inputId="PasswordInput" pHolderText="Enter password..." getInputValue={setPassword} />
        <FormInput inputId="RePasswordInput" pHolderText="Re-enter password..." getInputValue="" />
      </FormLayout>
    </div>
  )
}