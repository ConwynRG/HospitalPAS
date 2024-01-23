
var chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_4d0fb = new Chart(document.getElementById('chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_4d0fb'), {
    type: 'bar',
    data: {
        labels: [
            'testData', 'testData1000', 'testData200', 'testData500'
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    0, 33.55476402493321, 28.49487686688227, 22.83920312136637
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    7.530675355057467, 1.595725734639359, 0, 17.194646133174853
                  ]
                }, 
{
                  label: 'LAHC 400',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    1.0650540104090205, 44.480854853072124, 30.77542989314159, 13.729619389679854
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                  grouped: true,
                    borderWidth: 4
,
                  data: [
                    12.192476901729943, 41.59038290293856, 25.49739918574105, 27.260277512208987
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    12.16927207891534, 8.89047195013357, 34.44482819973416, 12.084959044985917
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    14.30885145598424, 0, 33.08214805279077, 0
                  ]
                }
        ]
    },
    options: {
        animation: false,
        responsive: true,
        maintainAspectRatio: false,
        resizeDelay: 100,
        spanGaps: true,
        plugins: {
            title: {
                display: true,
                text: 'Worst score calculation speed difference percentage summary (higher is better)'
            }
        },
        scales: {
            x: {
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Worst score calculation speed difference percentage'
                },
                ticks: {
                        stepSize: 1
                        
                },
                type: 'linear',
                display: true
            }
        },
watermark: {
    image: "website/webjars/timefold/img/timefold-logo-stacked-positive.svg",
    x: 15,
    y: 15,
    width: 48,
    height: 50,
    opacity: 0.1,
    alignX: "right",
    alignY: "bottom",
    alignToChartArea: true,
    position: "front",
}    },
plugins: [{ 
    id: 'customPlugin',
    beforeDraw: (chart, args, options) => {
          const ctx = chart.canvas.getContext('2d');
          ctx.save();
          ctx.globalCompositeOperation = 'destination-over';
          ctx.fillStyle = 'white';
          ctx.fillRect(0, 0, chart.canvas.width, chart.canvas.height);
          ctx.restore();
    }
}]
});

window.addEventListener('beforeprint', () => {
  chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_4d0fb.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_4d0fb.resize();
});