import LogoHeader from "Components/Headers/LogoHeader";
import MyPage from "Components/MyPage/MyPage";
import 'style/App.css'

function App() {
  return (
    <div className="App">
      <LogoHeader/>
      <MyPage userName="Parker Portslot" />
    </div>
  );
}

export default App;
