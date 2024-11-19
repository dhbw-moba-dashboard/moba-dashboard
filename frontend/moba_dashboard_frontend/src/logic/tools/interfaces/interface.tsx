//import react libraries
import type React from "react";

//create and export default interface with component properties
export interface DefaultComponentProps {
    id?: string | undefined,
    style?: React.CSSProperties,
    className?: string | undefined,
    children?: React.ReactNode
}