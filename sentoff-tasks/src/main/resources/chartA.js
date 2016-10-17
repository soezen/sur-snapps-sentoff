var margin = { top: 20, right: 20, bottom: 30, left: 50};
var width = 960 - margin.left - margin.right;
var height = 500 - margin.top - margin.bottom;

var x = d3.scaleLinear()
	.range([0, width]);

var y = d3.scaleLinear()
	.range([height, 0]);

var line = d3.line()
	.x(function(d) { return x(d.xValue); })
	.y(function(d) { return y(d.yValue); });

var svg = d3.select("body").append('svg')
	.attr('width', width + margin.left + margin.right)
	.attr('height', height + margin.top + margin.bottom)
	.append('g')
		.attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

var data = [
            { xValue: 1, yValue: 1},
            { xValue: 2, yValue: 2},
            { xValue: 3, yValue: 3},
            { xValue: 4, yValue: 4},
            { xValue: 5, yValue: 5}];

x.domain(d3.extent(data, function(d) { return d.xValue; }));
y.domain(d3.extent(data, function(d) { return d.yValue; }));

svg.append('g')
	.attr('class', 'axis axis--x')
	.attr('transform', 'translate(0,' + height + ')')
	.call(d3.axisBottom(x));

svg.append('g')
	.attr('class', 'axis axis--y')
	.call(d3.axisLeft(y))
	.append('text')
		.attr('class', 'axis-title')
		.attr('transform', 'rotate(-90)')
		.attr('y', 6)
		.attr('dy', '.71em')
		.style('text-anchor', 'end')
		.text('Price');

svg.append('path')
	.datum(data)
	.attr('class', 'line')
	.attr('stroke', 'steelblue')
	.attr('stroke-width', '1.5px')
	.attr('d', line);
document.elements['body'].children[0].toString();