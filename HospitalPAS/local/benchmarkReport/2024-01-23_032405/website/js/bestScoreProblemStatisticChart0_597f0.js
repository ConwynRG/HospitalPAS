
var chart_bestScoreProblemStatisticChart0_597f0 = new Chart(document.getElementById('chart_bestScoreProblemStatisticChart0_597f0'), {
    type: 'line',
    data: {
        labels: [
            167, 171, 177, 183, 186, 189, 192, 198, 216, 236, 297, 366, 533, 536, 568, 1663, 2700, 3354, 3711, 3974, 4020, 4044, 4883, 4914, 5742, 8141, 13671, 17964, 19067, 27150, 34210, 200721
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , -400, , -300, , , , , , , , , , , , -300, 0, , , , , , , , , , 0, , , , , 
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , , -400, , -300, , , , , , , , , -300, , , , , -250, 0, , , , , , , 0, 
                  ]
                }, 
{
                  label: 'LAHC 400',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    -400, , , -300, , , , , , , , -100, -50, , 0, , , , , , , , , , , , , , , , , 0
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 4
,
                  data: [
                    , , -400, , , -300, , , , , , , , 0, , , , , , , , , , , , , , 0, , , , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , , , -400, , , -300, , , , , , , , -300, -50, -50, 0, , , , , , , , 0, , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , -400, , , , , -300, , , , , , , , , , , , , , , -300, 0, , , 0, , , 
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
                text: 'testData500 best hard score statistic'
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
                        stepSize: 1000
                        ,
                        callback: function(value, index) {
                            return humanizeTime(value);
                        }
                },
                suggestedMin: 0,
                suggestedMax: 200721,
                type: 'linear',
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Best hard score'
                },
                ticks: {
                        stepSize: 10
                        
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
  chart_bestScoreProblemStatisticChart0_597f0.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreProblemStatisticChart0_597f0.resize();
});
