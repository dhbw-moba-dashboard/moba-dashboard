//import libraries
import type React from "react";

import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from "recharts";

//import css file
import "../../style/components_style/charts/graph.css";
import Text from "../atoms/texts";

//interface for chart component props
interface ChartComponentProps {
	yAxisText?: string,
	data?: any | any[] | []
}

//create and export chart component
export const ChartComponent: React.FC<ChartComponentProps> = (props: ChartComponentProps) => {
	//return created data chart component
	return (
		<div style={{marginTop: '4%'}}>
			<ResponsiveContainer width="100%" height={290}>
				{
					//check if data is passed or not
					props.data ? <LineChart data={props.data} margin={{ top: 20, right: 30, left: 20, bottom: 40 }}>
						<CartesianGrid strokeDasharray="3 3" stroke="#555" />
						<XAxis
							dataKey="name"
							stroke="#fff"
							tick={{ fontSize: 14, fill: "#fff" }}
							label={{ value: "Zeit", position: "bottom", offset: 10, fontSize: 16, fill: "#3fbbd7" }}
						/>
						<YAxis
							stroke="#fff"
							tick={{ fontSize: 14, fill: "#fff" }}
							label={{ value: props.yAxisText, angle: -90, position: "left", offset: -15, fontSize: 16, fill: "#3fbbd7" }}
						/>
						<Tooltip
							contentStyle={{ backgroundColor: "#333", borderRadius: "8px", color: "#fff" }}
							itemStyle={{ color: "#fff" }}
							cursor={{ stroke: "#3fbbd7", strokeWidth: 1, strokeDasharray: "3 3" }}
						/>
						<Line
							type="monotone"
							dataKey="value"
							stroke="#3fbbd7"
							strokeWidth={2}
							dot={{ r: 4, strokeWidth: 2, stroke: "#3fbbd7", fill: "#fff" }}
							activeDot={{ r: 6, strokeWidth: 2, stroke: "#3fbbd7", fill: "#3fbbd7" }}
						/>
					</LineChart> : <Text textValue="No data to show"/>
				}
			</ResponsiveContainer>
		</div>
	);
};