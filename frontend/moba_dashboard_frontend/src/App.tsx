//import i18n
import i18n from "i18next";
import React from "react";

//import app styling
import "./App.css";

//import custom react components
import { ImageButton } from "./components/atoms/buttons";
import { HeaderText } from "./components/atoms/texts";
import FlexBox from "./components/container/FlexBox";

import MainLeftSideContainer from "./components/views/left_side/main_left_side_container";
//import container view component
import MyTrainsContainer from "./components/views/right_side/my_trains_view";

function App() {
	//define function to change language
	const changeLanguage = (lng: string) => {
		i18n.changeLanguage(lng);
	};

	//return created ui components
	return (
		<div className="App">
			<FlexBox
				style={{ justifyContent: "space-between", alignItems: "center" }}
			>
				<HeaderText textValue="we.love.trains" />
				<ImageButton buttonAction={changeLanguage} />
			</FlexBox>
			<FlexBox style={{ justifyContent: "space-between" }}>
				<div style={{ width: "40%" }}>
					<MainLeftSideContainer />
				</div>
				<div style={{ width: "59%" }}>
					<MyTrainsContainer />
				</div>
			</FlexBox>
		</div>
	);
}

export default App;
