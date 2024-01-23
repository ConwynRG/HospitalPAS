
var chart_bestScoreProblemStatisticChart1_9e2c7 = new Chart(document.getElementById('chart_bestScoreProblemStatisticChart1_9e2c7'), {
    type: 'line',
    data: {
        labels: [
            427, 464, 481, 505, 513, 540, 638, 873, 1400, 1652, 2264, 2571, 2897, 3983, 4729, 4796, 5605, 6052, 6364, 6747, 6771, 7543, 7631, 7702, 9344, 9873, 10906, 14729, 16052, 16747, 17631, 17839, 20550, 20557, 20906, 30557
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , -14500, , , , , , -14250, , -14050, , , , , , , , , , , , , -13750, , -13600, , , , , , , , -13600, 
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , -14500, , , , , , , -14300, , , , , , , , , -14050, -13900, , , , , , , , -13900, , , , , 
                  ]
                }, 
{
                  label: 'LAHC 400',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , -14500, , -14450, -14400, -14300, , , , , , , , , -14200, , , , , , , , , , , -14200, , , , , , , 
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 4
,
                  data: [
                    , -14500, , , , , , , , , -14300, , , -14150, , , -13950, , -13900, , -13700, , , -13650, , -13600, , , , , , -13450, -13400, -13300, , -13300
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , -14500, , , , , , , , , , , , , -14300, , , , -14050, , , , , , , , , , -14050, , , , , , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    -14500, , , , , , , , , , , , , , -14300, , , , , , , , , , , , , -14300, , , , , , , , 
                  ]
                }
        ]
    },
    options: {
        animation: false,
        responsive: true,
        maintainAspectRatio: false,
        spanGaps: true,
        plugins: {
            title: {
                display: true,
                text: 'testData1000 best soft score statistic'
            },
            tooltip: {
                callbacks: {
                        title: function(context) {
                            return humanizeTime(context[0].parsed.x);
                        }
                        
                }
            }
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Time spent'
                },
                ticks: {
                        stepSize: 100
                        ,
                        callback: function(value, index) {
                            return humanizeTime(value);
                        }
                },
                suggestedMin: 0,
                suggestedMax: 30557,
                type: 'linear',
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Best soft score'
                },
                ticks: {
                        stepSize: 100
                        
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
  chart_bestScoreProblemStatisticChart1_9e2c7.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreProblemStatisticChart1_9e2c7.resize();
});
