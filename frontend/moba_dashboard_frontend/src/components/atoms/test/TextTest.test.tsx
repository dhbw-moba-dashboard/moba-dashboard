//import testing libraries
import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";

//impiort component
import Text from "../texts";

//test suite
describe("General Text Component Test", () => {
    //render test
    it("renders text component correctly", () => {
        render(<Text textValue="Render Test"/>)
        const buttonElement = screen.getByText("Render Test");
        expect(buttonElement).toBeInTheDocument();
    });

    //set text test
    it("sets text component text correctly", () => {
        render(<Text textValue="Correct Text Component Text"/>);

        const textElement = screen.getByText("Correct Text Component Text");
        expect(textElement).toBeInTheDocument();
        expect(textElement).toHaveTextContent("Correct Text Component Text");
    });
});

