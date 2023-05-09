import { useEffect, useState } from "react"
import './StartedEventBar.css'
export default function StartedEventBar() {

  const [ms, setMs] = useState(0);
  const [running, setRunning] = useState(false); 

  useEffect(() => {
    let interval;
    if(running) {
      interval = setInterval(() => {
        setMs(prev => prev + 1)
      }, 1000)
    } else {
      clearInterval(interval)
    }
  }, [running])

  return (
    <div id="started-event-bar">
      <p>ongoing event:  {
        ("0" + Math.floor((ms / 3600) % 60)).slice(-2)}
        :
        {("0" + Math.floor((ms / 60) % 60)).slice(-2)}
        :
        {("0" + Math.floor((ms) % 60)).slice(-2)}</p>
      <button onClick={()=> setRunning(prev => !prev)}>
        stop
      </button>
    </div>
  )
}