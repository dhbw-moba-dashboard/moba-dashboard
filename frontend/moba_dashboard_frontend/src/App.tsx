import React from 'react';
import './App.css';
import FlexBox from "./components/container/FlexBox";
import {HeaderText} from "./components/atoms/texts";
import {ImageButton} from "./components/atoms/buttons";

function App() {
  return (
    <div className="App">
      <FlexBox style={{justifyContent: 'space-between', alignItems: 'center'}}>
          <HeaderText textValue="we.love.trains"/>
          <ImageButton style={{height: '28px', width: 'auto'}} buttonImage="images/general/Icon_Refresh_White.png"/>
      </FlexBox>
      <FlexBox style={{justifyContent: 'space-between'}}>
        <div style={{width: '40%', backgroundColor: 'white'}}>
          left_container


        </div>
        <div style={{width: '59%', backgroundColor: 'red'}}>
          right_container

            
        </div>
      </FlexBox>
    </div>
  );
}

export default App;
