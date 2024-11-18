//import libraries
import type React from "react";

//import css file
import "../../style/components_style/atoms/texts.css";

//import custom react files
import {DefaultComponentProps} from "../../logic/tools/interfaces/interface";

//import custom react components
import FlexBox from "../container/FlexBox";
import Image from "./images";

//define interface for text properties
interface TextProperties extends DefaultComponentProps {
	textValue: string;
	textLinkUrl?: string | undefined;
	textImage?: string | undefined;
}

//create text component
const Text: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<span id={props.id} style={props.style} className={props.className}>
			{props.textValue}
		</span>
	);
};
//export default text component
export default Text;

//create and export header text component
export const HeaderText: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<Text
			id={props.id}
			style={props.style}
			className="header-text-component-style-properties"
			textValue={props.textValue}
		/>
	);
};

//create and export text link component
export const TextLink: React.FC<TextProperties> = (props: TextProperties) => {
	return (
		<a id={props.id} style={props.style} className={props.className}>
			{props.className}
		</a>
	);
};

//create and export image text link component
export const ImageLink: React.FC<TextProperties> = (props: TextProperties) => {
	//function to execute onclick event
	function executeOnClickEvent() {
		//check if link is set
		if (props.textLinkUrl) {
			window.open(
				!props.textValue.includes("https://")
					? `https://${props.textLinkUrl}`
					: props.textLinkUrl,
				"_blank",
			);
		}
	}

	//return created component
	return (
		<FlexBox id={props.id} clickAction={executeOnClickEvent}>
			<Image
				style={{ height: "18px", width: "auto", marginRight: "2%" }}
				imageValue={props.textImage}
			/>
			<TextLink
				style={props.style}
				className={props.className}
				textValue={props.textValue}
			/>
		</FlexBox>
	);
};
