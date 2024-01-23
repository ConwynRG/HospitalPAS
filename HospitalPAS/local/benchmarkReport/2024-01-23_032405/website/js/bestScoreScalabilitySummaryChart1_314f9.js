
var chart_bestScoreScalabilitySummaryChart1_314f9 = new Chart(document.getElementById('chart_bestScoreScalabilitySummaryChart1_314f9'), {
    type: 'line',
    data: {
        labels: [
            54, 17200, 64000, 155000
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                    borderWidth: 1
                  ,
                  data: [
                    -650, -700, -2450, -13600
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                    borderWidth: 1
                  ,
                  data: [
                    -350, -800, -1600, -13900
                  ]
                }, 
{
                  label: 'LAHC 400',
                    borderWidth: 1
                  ,
                  data: [
                    -650, -1950, -6300, -14200
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                    borderWidth: 4
,
                  data: [
                    -650, -700, -1600, -13300
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                    borderWidth: 1
                  ,
                  data: [
                    -650, -800, -1850, -14050
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                    borderWidth: 1
                  ,
                  data: [
                    -650, -800, -2750, -14300
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
                text: 'Best soft score scalability summary (higher is better)'
            },
            tooltip: {
                callbacks: {
                }
            }
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Problem scale'
                },
                ticks: {
                        stepSize: 1000
                        
                },
                suggestedMin: 0,
                suggestedMax: 155000,
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
  chart_bestScoreScalabilitySummaryChart1_314f9.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreScalabilitySummaryChart1_314f9.resize();
});
