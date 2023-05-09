import './LoginForm.css'
import FormLayout from "Layouts/FormLayout/FormLayout";
import FormInput from "Components/forms/FormInput/FormInput";
import { useState } from "react";
import SharkParkLogo from "Components/SharkParkLogo/SharkParkLogo";

export default function LoginForm({changeForm}){
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

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
    <div id="login-form">
      <div id="shark-park-logo">
        <SharkParkLogo />
      </div>
      <FormLayout msg="First time here?" redirectMsg="Sign up" redirectFn={changeForm} btnText="login">
        <FormInput inputId="EmailInput" pHolderText="Enter email..." getInputValue={setEmail} />
        <FormInput inputId="PasswordInput" pHolderText="Enter password..." getInputValue={setPassword} />
      </FormLayout>
    </div>
  )
}