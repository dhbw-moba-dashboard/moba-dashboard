//import libraries
import React from "react";
import {Chart} from "react-google-charts";

//import css file
import "../../style/components_style/charts/graph.css";

//create and export chart component
export const ChartComponent: React.FC = () => {
    //define chart data
    const data = [
        ["Year", "Sales", "Expenses"],
        ["2018", 1000, 400],
        ["2019", 1170, 460],
        ["2020", 660, 1120],
        ["2021", 1030, 540],
    ];

    //define chart options
    const options = {
        title: "Company Performance",
        curveType: "function",
        legend: { position: "bottom" },
    };

    //return created react component
    return (
        <>
            <Chart
                chartType="LineChart"
                width="100%"
                height="400px"
                data={data}
                options={options}
            />
        </>
    );
}
