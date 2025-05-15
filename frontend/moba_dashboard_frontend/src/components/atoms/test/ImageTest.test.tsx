//import testing libraries
import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";

//impiort component
import Image from "../images";

//test suite
describe("General Image Test", () => {
    //render test
    it("renders image correctly", () => {
        render(<Image imageValue="images/color/Icon_Live_Color.png" imageAltText="Render Test"/>)
        const imageElement = screen.getByAltText("Render Test");
        expect(imageElement).toBeInTheDocument();
    });
});

