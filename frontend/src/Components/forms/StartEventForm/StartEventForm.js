import './StartEventForm.css'
import FormLayout from "Layouts/FormLayout/FormLayout";
import FormInput from "Components/forms/FormInput/FormInput";
import { useEffect, useState } from "react";
import SharkParkLogo from "Components/SharkParkLogo/SharkParkLogo";
import FormTitle from '../FormTitle/FormTitle';

export default function StartEventForm({changeForm}){

  const [token, setToken] = useState()

  useEffect(() => {
    setToken(
      localStorage.getItem("token")
    )
  }, [])

  const [formData, setFormData] = useState()
  
  useEffect(()=> {
    setFormData(
      {
        userId: "",
        carId: "",
        slotId: "",
      }
    )
  }, [])


  /*============================================ *\
       ---------- SET DATA FUNCTION ---------
  \*============================================ */

  function setUserId(data){
    setFormData(prev => {
      return {...prev, userId: data}
    })  
  }

  function setCarId(data){
    setFormData(prev => {
      return {...prev, carId: data}
    })  
  }

  function setSlotId(data){
    setFormData(prev => {
      return {...prev, slotId: data}
    })  
  }

  /*============================================ *\
       ---------- Post Request ---------
  \*============================================ */
  
  async function sendData(){
    const data = await fetch( "", {
      method: 'POST',
      headers: {
      'Content-Type': 'application/json',
      'Authorization' : `Bearer ${token}`
      },
      body: JSON.stringify(formData)
    })
  }

  return (
    <div id="start-event-form">
      <div id="shark-park-logo">
        <FormTitle text="Create a parking event" />
      </div>
      <FormLayout redirectFn={() => changeForm(true)} btnText="start parking">
        <FormInput inputId="userID-input" pHolderText="Enter user id..." getInputValue={setUserId} />
        <FormInput inputId="carID-input" pHolderText="Enter car id..." getInputValue={setCarId} />
        <FormInput inputId="slotID-input" pHolderText="Enter parking slot id..." getInputValue={setSlotId} />
      </FormLayout>
    </div>
  )
}