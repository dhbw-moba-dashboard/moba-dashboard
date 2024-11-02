//import libraries
import React from "react";

//import css file
import '../../style/components_style/atoms/buttons.css';

//import custom component
import Image from "./images";

//define interface for button properties
interface ButtonProperties {
    buttonID?: string | undefined,
    buttonText?: string | undefined,
    buttonImage?: string | undefined,
    buttonChecked?: boolean | undefined,
    style?: React.CSSProperties,
    imageStyle?: React.CSSProperties,
    className?: string | undefined,
    buttonAction?: () => void
}

//create image text button
const TextButton: React.FC<ButtonProperties> = (props: ButtonProperties) => {
    return (
        <button id={props.buttonID} className={props.className} style={props.style} onClick={props.buttonAction}>
            {props.buttonText}
        </button>
    );
}
//export text button
export default TextButton;

//create and export image button component
export const ImageButton: React.FC<ButtonProperties> = (props: ButtonProperties) => {
    return (
        <button id={props.buttonID} onClick={props.buttonAction} className={props.className}>
            <Image style={{height: '18px', width: 'auto', ...props.style}} imageValue={props.buttonImage}/>
        </button>
    );
}