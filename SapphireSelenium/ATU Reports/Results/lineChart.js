            $(document).ready(function(){
                var line1 = [15,2,2,2,2,2,15,15,16,17];
                var line2 = [8,1,1,1,1,1,8,8,10,31];
                var line3 = [0,0,0,0,0,0,0,0,21,0];

var ticks = [1,2,3,4,5,6,7,8,9,10];$.jqplot('line', [line1, line2, line3], {
        animate: true,
axesDefaults:{min:0,tickInterval: 1},        seriesDefaults: {
            rendererOptions: {
                smooth: true
            }
        },
        series: [{lineWidth: 1.5, label: 'Passed'},
            {lineWidth: 1.5, label: 'Failed'},
            {lineWidth: 1.5, label: 'Skipped'}],
        axes: {
            xaxis: {
                label: "Run Number",
                ticks: ticks,
                tickOptions: {
                    formatString: "%'d Run"
                },
                pad: 1.2,
                rendererOptions: {
                    tickInset: 0.3,
                    minorTicks: 1
                }
            },
            yaxis: {
                label: "TC Number"
                ,tickOptions: {
                    formatString: "%'d Tc"
                },
            }
        },
        highlighter: {
            show: true,
            sizeAdjust: 10,
            tooltipLocation: 'n',
            tooltipAxes: 'y',
            tooltipFormatString: '%d :&nbsp;<b><i><span style="color:black;">Test Cases</span></i></b>',
            useAxesFormatters: false
        },
        cursor: {
            show: true
        },
        grid: {background: '#ffffff', drawGridLines: true, gridLineColor: '#cccccc', borderColor: '#cccccc',
            borderWidth: 0.5, shadow: false},
        legend: {show: true, placement: 'outside', location: 'e'},
        seriesColors: ["#7BB661", "#E03C31", "#21ABCD"]
    });
});
