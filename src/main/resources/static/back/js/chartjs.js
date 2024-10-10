$(function() {
	'use strict';
	var data = {
		labels: ["2013", "2014", "2014", "2015", "2016", "2017"],
		datasets: [{
			label: '# of Votes',
			data: [10, 19, 3, 5, 2, 3],
			backgroundColor: [
				'rgba(255, 66, 15, 0.7)',
				'rgba(0, 187, 221, 0.7)',
				'rgba(255, 193, 7, 0.7)',
				'rgba(0, 182, 122, 0.7)',
				'rgba(153, 102, 255, 0.7)',
				'rgba(255, 159, 64, 0.7)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)',
				'rgba(0, 187, 221, 1)',
				'rgba(255, 193, 7, 1)',
				'rgba(0, 182, 122, 1)',
				'rgba(153, 102, 255, 1)',
				'rgba(255, 159, 64, 1)'
			],
			borderWidth: 1,
			fill: false
		}]
	};
	var dataDark = {
		labels: ["2013", "2014", "2014", "2015", "2016", "2017"],
		datasets: [{
			label: '# of Votes',
			data: [10, 19, 3, 5, 2, 3],
			backgroundColor: [
				'rgba(255, 66, 15, 0.7)',
				'rgba(0, 187, 221, 0.7)',
				'rgba(255, 193, 7, 0.7)',
				'rgba(0, 182, 122, 0.7)',
				'rgba(153, 102, 255, 0.7)',
				'rgba(255, 159, 64, 0.7)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)',
				'rgba(0, 187, 221, 1)',
				'rgba(255, 193, 7, 1)',
				'rgba(0, 182, 122, 1)',
				'rgba(153, 102, 255, 1)',
				'rgba(255, 159, 64, 1)'
			],
			borderWidth: 1,
			fill: false
		}]
	};
	var multiLineData = {
		labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
		datasets: [{
			label: 'Dataset 1',
			data: [12, 19, 3, 5, 2, 3],
			borderColor: [
				'#587ce4'
			],
			borderWidth: 2,
			fill: false
		},
		{
			label: 'Dataset 2',
			data: [5, 23, 7, 12, 42, 23],
			borderColor: [
				'#ede190'
			],
			borderWidth: 2,
			fill: false
		},
		{
			label: 'Dataset 3',
			data: [15, 10, 21, 32, 12, 33],
			borderColor: [
				'#f44252'
			],
			borderWidth: 2,
			fill: false
		}
		]
	};
	var options = {
		scales: {
			yAxes: [{
				ticks: {
					beginAtZero: true
				}
			}]
		},
		legend: {
			display: false
		},
		elements: {
			point: {
				radius: 0
			}
		}

	};
	var optionsDark = {
		scales: {
			yAxes: [{
				ticks: {
					beginAtZero: true
				},
				gridLines: {
					color: '#322f2f',
					zeroLineColor: '#322f2f'
				}
			}],
			xAxes: [{
				ticks: {
					beginAtZero: true
				},
				gridLines: {
					color: '#322f2f',
				}
			}],
		},
		legend: {
			display: false
		},
		elements: {
			point: {
				radius: 0
			}
		}

	};
	var doughnutPieData = {
		datasets: [{
			data: [30, 40, 30],
			backgroundColor: [
				'rgba(255, 66, 15, 0.8)',
				'rgba(0, 187, 221, 0.8)',
				'rgba(255, 193, 7, 0.8)',
				'rgba(0, 182, 122, 0.8)',
				'rgba(153, 102, 255, 0.8)',
				'rgba(255, 159, 64, 0.8)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)',
				'rgba(0, 187, 221, 1)',
				'rgba(255, 193, 7, 1)',
				'rgba(0, 182, 122, 1)',
				'rgba(153, 102, 255, 1)',
				'rgba(255, 159, 64, 1)'
			],
		}],
		labels: [
			'Red',
			'Blue',
			'Yellow',
		]
	};
	var doughnutPieOptions = {
		responsive: true,
		animation: {
			animateScale: true,
			animateRotate: true
		}
	};
	var areaData = {
		labels: ["2013", "2014", "2015", "2016", "2017"],
		datasets: [{
			label: '# of Votes',
			data: [12, 19, 3, 5, 2, 3],
			backgroundColor: [
				'rgba(255, 66, 15, 0.4)',
				'rgba(0, 187, 221, 0.4)',
				'rgba(255, 193, 7, 0.4)',
				'rgba(0, 182, 122, 0.4)',
				'rgba(153, 102, 255, 0.4)',
				'rgba(255, 159, 64, 0.4)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)',
				'rgba(0, 187, 221, 1)',
				'rgba(255, 193, 7, 1)',
				'rgba(0, 182, 122, 1)',
				'rgba(153, 102, 255, 1)',
				'rgba(255, 159, 64, 1)'
			],
			borderWidth: 1,
			fill: true,
		}]
	};
	var areaDataDark = {
		labels: ["2013", "2014", "2015", "2016", "2017"],
		datasets: [{
			label: '# of Votes',
			data: [12, 19, 3, 5, 2, 3],
			backgroundColor: [
				'rgba(255, 66, 15, 0.4)',
				'rgba(0, 187, 221, 0.4)',
				'rgba(255, 193, 7, 0.4)',
				'rgba(0, 182, 122, 0.4)',
				'rgba(153, 102, 255, 0.4)',
				'rgba(255, 159, 64, 0.4)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)',
				'rgba(0, 187, 221, 1)',
				'rgba(255, 193, 7, 1)',
				'rgba(0, 182, 122, 1)',
				'rgba(153, 102, 255, 1)',
				'rgba(255, 159, 64, 1)'
			],
			borderWidth: 1,
			fill: true, // 3: no fill
		}]
	};
	var areaOptions = {
		plugins: {
			filler: {
				propagate: true
			}
		}
	}
	var areaOptionsDark = {
		scales: {
			yAxes: [{
				ticks: {
					beginAtZero: true
				},
				gridLines: {
					color: '#322f2f',
					zeroLineColor: '#322f2f'
				}
			}],
			xAxes: [{
				ticks: {
					beginAtZero: true
				},
				gridLines: {
					color: '#322f2f',
				}
			}],
		},
		plugins: {
			filler: {
				propagate: true
			}
		}
	}
	var multiAreaData = {
		labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
		datasets: [{
			label: 'Facebook',
			data: [8, 11, 13, 15, 12, 13, 16, 15, 13, 19, 11, 14],
			borderColor: ['rgba(255, 66, 15, 0.5)'],
			backgroundColor: ['rgba(255, 66, 15, 0.5)'],
			borderWidth: 1,
			fill: true
		},
		{
			label: 'Twitter',
			data: [7, 17, 12, 16, 14, 18, 16, 12, 15, 11, 13, 9],
			borderColor: ['rgba(0, 187, 221, 0.5)'],
			backgroundColor: ['rgba(0, 187, 221, 0.5)'],
			borderWidth: 1,
			fill: true
		},
		{
			label: 'Linkedin',
			data: [6, 14, 16, 20, 12, 18, 15, 12, 17, 19, 15, 11],
			borderColor: ['rgba(255, 193, 7, 0.5)'],
			backgroundColor: ['rgba(255, 193, 7, 0.5)'],
			borderWidth: 1,
			fill: true
		}
		]
	};
	var multiAreaOptions = {
		plugins: {
			filler: {
				propagate: true
			}
		},
		elements: {
			point: {
				radius: 0
			}
		},
		scales: {
			xAxes: [{
				gridLines: {
					display: false
				}
			}],
			yAxes: [{
				gridLines: {
					display: false
				}
			}]
		}
	}
	var scatterChartData = {
		datasets: [{
			label: 'First Dataset',
			data: [{
				x: -10,
				y: 0
			},
			{
				x: 0,
				y: 3
			},
			{
				x: -25,
				y: 5
			},
			{
				x: 40,
				y: 5
			}
			],
			backgroundColor: [
				'rgba(255, 66, 15, 0.7)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)'
			],
			borderWidth: 1
		},
		{
			label: 'Second Dataset',
			data: [{
				x: 10,
				y: 5
			},
			{
				x: 20,
				y: -30
			},
			{
				x: -25,
				y: 15
			},
			{
				x: -10,
				y: 5
			}
			],
			backgroundColor: [
				'rgba(0, 187, 221, 0.7)',
			],
			borderColor: [
				'rgba(0, 187, 221, 1)',
			],
			borderWidth: 1
		}]
	}
	var scatterChartDataDark = {
		datasets: [{
			label: 'First Dataset',
			data: [{
				x: -10,
				y: 0
			},
			{
				x: 0,
				y: 3
			},
			{
				x: -25,
				y: 5
			},
			{
				x: 40,
				y: 5
			}
			],
			backgroundColor: [
				'rgba(255, 66, 15, 0.7)'
			],
			borderColor: [
				'rgba(255, 66, 15,1)'
			],
			borderWidth: 1
		},
		{
			label: 'Second Dataset',
			data: [{
				x: 10,
				y: 5
			},
			{
				x: 20,
				y: -30
			},
			{
				x: -25,
				y: 15
			},
			{
				x: -10,
				y: 5
			}
			],
			backgroundColor: [
				'rgba(0, 187, 221, 0.7)',
			],
			borderColor: [
				'rgba(0, 187, 221, 1)',
			],
			borderWidth: 1
		}
		]
	}
	var scatterChartOptions = {
		scales: {
			xAxes: [{
				type: 'linear',
				position: 'bottom'
			}]
		}
	}
	var scatterChartOptionsDark = {
		scales: {
			xAxes: [{
				type: 'linear',
				position: 'bottom',
				gridLines: {
					color: '#322f2f',
					zeroLineColor: '#322f2f'
				}
			}],
			yAxes: [{
				gridLines: {
					color: '#322f2f',
					zeroLineColor: '#322f2f'
				}
			}]
		}
	}
	if ($("#barChart").length) {
		var barChartCanvas = $("#barChart").get(0).getContext("2d");
		var barChart = new Chart(barChartCanvas, {
			type: 'bar',
			data: data,
			options: options
		});
	}
	if ($("#barChartDark").length) {
		var barChartCanvasDark = $("#barChartDark").get(0).getContext("2d");
		var barChartDark = new Chart(barChartCanvasDark, {
			type: 'bar',
			data: dataDark,
			options: optionsDark
		});
	}
	if ($("#lineChart").length) {
		var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
		var lineChart = new Chart(lineChartCanvas, {
			type: 'line',
			data: data,
			options: options
		});
	}
	if ($("#lineChartDark").length) {
		var lineChartCanvasDark = $("#lineChartDark").get(0).getContext("2d");
		var lineChartDark = new Chart(lineChartCanvasDark, {
			type: 'line',
			data: dataDark,
			options: optionsDark
		});
	}
	if ($("#linechart-multi").length) {
		var multiLineCanvas = $("#linechart-multi").get(0).getContext("2d");
		var lineChart = new Chart(multiLineCanvas, {
			type: 'line',
			data: multiLineData,
			options: options
		});
	}
	if ($("#areachart-multi").length) {
		var multiAreaCanvas = $("#areachart-multi").get(0).getContext("2d");
		var multiAreaChart = new Chart(multiAreaCanvas, {
			type: 'line',
			data: multiAreaData,
			options: multiAreaOptions
		});
	}
	if ($("#doughnutChart").length) {
		var doughnutChartCanvas = $("#doughnutChart").get(0).getContext("2d");
		var doughnutChart = new Chart(doughnutChartCanvas, {
			type: 'doughnut',
			data: doughnutPieData,
			options: doughnutPieOptions
		});
	}
	if ($("#pieChart").length) {
		var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
		var pieChart = new Chart(pieChartCanvas, {
			type: 'pie',
			data: doughnutPieData,
			options: doughnutPieOptions
		});
	}
	if ($("#areaChart").length) {
		var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
		var areaChart = new Chart(areaChartCanvas, {
			type: 'line',
			data: areaData,
			options: areaOptions
		});
	}
	if ($("#areaChartDark").length) {
		var areaChartCanvas = $("#areaChartDark").get(0).getContext("2d");
		var areaChart = new Chart(areaChartCanvas, {
			type: 'line',
			data: areaDataDark,
			options: areaOptionsDark
		});
	}
	if ($("#scatterChart").length) {
		var scatterChartCanvas = $("#scatterChart").get(0).getContext("2d");
		var scatterChart = new Chart(scatterChartCanvas, {
			type: 'scatter',
			data: scatterChartData,
			options: scatterChartOptions
		});
	}
	if ($("#scatterChartDark").length) {
		var scatterChartCanvas = $("#scatterChartDark").get(0).getContext("2d");
		var scatterChart = new Chart(scatterChartCanvas, {
			type: 'scatter',
			data: scatterChartDataDark,
			options: scatterChartOptionsDark
		});
	}
	if ($("#browserTrafficChart").length) {
		var doughnutChartCanvas = $("#browserTrafficChart").get(0).getContext("2d");
		var doughnutChart = new Chart(doughnutChartCanvas, {
			type: 'doughnut',
			data: browserTrafficData,
			options: doughnutPieOptions
		});
	}
});