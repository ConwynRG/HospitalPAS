
var chart_bestScorePerTimeSpentSummaryChart0_935a0 = new Chart(document.getElementById('chart_bestScorePerTimeSpentSummaryChart0_935a0'), {
    type: 'line',
    data: {
        labels: [
            10007, 10008, 10011, 10063, 10074, 10921, 14114, 14273, 14729, 14730, 16052, 16747, 17631, 17964, 18516, 19067, 20906, 27150, 30557, 34210, 39712, 200721
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , 0, , , , , , , , , 0, , -1650, , , , , 
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                    borderWidth: 1
                  ,
                  data: [
                    -100, , , , , , , 0, , , , , -1650, , , , , , , 0, , 
                  ]
                }, 
{
                  label: 'LAHC 400',
                    borderWidth: 1
                  ,
                  data: [
                    , , 0, , , , , , , , -1650, , , , , , , , , , 0, 0
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                    borderWidth: 4
,
                  data: [
                    , 0, , , , , , , , , , , , 0, , , , , -1650, , , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                    borderWidth: 1
                  ,
                  data: [
                    , , , 0, , , , , , 0, , -1650, , , , , , 0, , , , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                    borderWidth: 1
                  ,
                  data: [
                    , , , , 0, , 0, , -1650, , , , , , , 0, , , , , , 
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
                text: 'Best hard score per time spent summary (higher left is better)'
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
  chart_bestScorePerTimeSpentSummaryChart0_935a0.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScorePerTimeSpentSummaryChart0_935a0.resize();
});
