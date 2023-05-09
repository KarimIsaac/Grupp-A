import SecondaryBtn from "Components/btns/SecondaryBtn";
import EventCard from "Components/EventCard/EventCard";
import AddCarForm from "Components/forms/AddCarForm/AddCarForm";
import StartEventForm from "Components/forms/StartEventForm/StartEventForm"
import MyParkingEvents from "Components/MyParkingEvents/MyParkingEvents";
import StartedEventBar from "Components/StartedEventBar/startedEventBar";
import LoginService from "keycloak";
import VerticalMenuLayout from "Layouts/VerticalMenuLayout/VerticalMenuLayout";
import { useState } from "react"
import "./MyPage.css"

export default function MyPage ({userName}) {
  const [child, setChild] = useState();
  const [startedEvent, setStartedEvent] = useState(false)
  const [running, setRunning] = useState(false); 
  
  function getAddCarForm(){
    setChild(<AddCarForm/>)
  }

  function getMyParkingCard(){
    setChild(
      <MyParkingEvents ChangeForm={setChild}>
         <EventCard btnText="pay" price="30" dateTime="2022/02/12 11:40-12:23" />
         <EventCard btnText="pay" price="30" dateTime="2022/02/12 11:40-12:23" />
         <EventCard btnText="pay" price="30" dateTime="2022/02/12 11:40-12:23" />
      </MyParkingEvents>
    )
  }

  function getStartParkingForm(){
    setChild(<StartEventForm changeForm={setStartedEvent} />)
  }

  return (
    <section id="my-page">
      <VerticalMenuLayout userName={localStorage.getItem("name")}> 
        <SecondaryBtn text="start parking" clickFn={getStartParkingForm}/>
        <SecondaryBtn text="my parkings" clickFn={getMyParkingCard}/>
        <SecondaryBtn text="add car" clickFn={getAddCarForm} />
      </VerticalMenuLayout>
      <div id="my-page-body">
        { startedEvent ? <StartedEventBar /> : <></> }
        <div className="form-container">
          {child}
        </div>
      </div>
    </section>
  )
}