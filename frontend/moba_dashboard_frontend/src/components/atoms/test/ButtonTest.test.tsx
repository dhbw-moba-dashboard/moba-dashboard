//import testing libraries
import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";

//impiort component
import TextButton from "../buttons";

//test suite
describe("General Button Test", () => {
    //render button test
    it("renders button correctly", () => {
        render(<TextButton buttonText="Render Test"/>)
        const buttonElement = screen.getByText("Render Test");
        expect(buttonElement).toBeInTheDocument();
    });

    //set text test
    it("sets button test correctly", () => {
        render(<TextButton buttonText="Correct Button Text"/>);

        const buttonElement = screen.getByRole("button", {name: "Correct Button Text"});
        expect(buttonElement).toBeInTheDocument();
        expect(buttonElement).toHaveTextContent("Correct Button Text");
    });
});

