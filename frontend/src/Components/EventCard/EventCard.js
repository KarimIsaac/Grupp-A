import './EventCard.css'

export default function EventCard({price, dateTime, btnText}){
  return(
    <article className="event-card">
      <div className="EventCard-inner-card">
        <div className="EventCard-header-container">
        <h3>
          {price}
          </h3>
        </div>
        <div className="EventCard-text-container">
          <p>
            {dateTime}
            </p> 
        </div>
        <div className="EventCard-btn-container">
          <button> 
            {btnText} 
          </button>
        </div>
      </div>
    </article>
  )
}