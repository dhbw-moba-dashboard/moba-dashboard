//import libraries
import React from "react";

//import css file
import '../../style/components_style/buttons.css';

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