//import libraries
import type React from "react";

//import css file
import "../../style/components_style/atoms/buttons.css";

//import custom react files
import type { DefaultComponentProps } from "../../logic/tools/interfaces/interface";

//import custom component
import Image from "./images";

//define interface for button properties
interface ButtonProperties extends DefaultComponentProps {
	buttonText?: string | undefined;
	buttonImage?: string | undefined;
	buttonChecked?: boolean | undefined;
	imageStyle?: React.CSSProperties;
	buttonAction?: () => void;
}

//create image text button
const TextButton: React.FC<ButtonProperties> = (props: ButtonProperties) => {
	return (
		<button
			id={props.id}
			className={props.className}
			style={props.style}
			onClick={props.buttonAction}
		>
			{props.buttonText}
		</button>
	);
};
//export text button
export default TextButton;

//create and export image button component
export const ImageButton: React.FC<ButtonProperties> = (
	props: ButtonProperties,
) => {
	return (
		<button
			id={props.id}
			onClick={props.buttonAction}
			className={props.className}
		>
			<Image
				style={{ height: "18px", width: "auto", ...props.style }}
				imageValue={props.buttonImage}
			/>
		</button>
	);
};

//create and export glass morph button component
export const GlassMorphButton: React.FC<ButtonProperties> = (
	props: ButtonProperties,
) => {
	return (
		<button
			id={props.id}
			className={`glass-morph-button-style-properties ${props.className}`}
			style={props.style}
			onClick={props.buttonAction}
		>
			{props.children}
		</button>
	);
};
