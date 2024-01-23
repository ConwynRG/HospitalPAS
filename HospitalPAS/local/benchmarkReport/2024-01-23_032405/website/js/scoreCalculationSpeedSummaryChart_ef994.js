
var chart_scoreCalculationSpeedSummaryChart_ef994 = new Chart(document.getElementById('chart_scoreCalculationSpeedSummaryChart_ef994'), {
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
                    211163, 122773, 95080, 74991
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                    borderWidth: 1
                  ,
                  data: [
                    227065, 95547, 90711, 57046
                  ]
                }, 
{
                  label: 'LAHC 400',
                    borderWidth: 1
                  ,
                  data: [
                    213412, 124952, 88029, 81126
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                    borderWidth: 4
,
                  data: [
                    236909, 119909, 98502, 79503
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                    borderWidth: 1
                  ,
                  data: [
                    236860, 128458, 86756, 61142
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                    borderWidth: 1
                  ,
                  data: [
                    241378, 127156, 77402, 56150
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
                text: 'Score calculation speed summary (higher is better)'
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
                    text: 'Score calculation speed per second'
                },
                ticks: {
                        stepSize: 1000
                        
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
  chart_scoreCalculationSpeedSummaryChart_ef994.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_scoreCalculationSpeedSummaryChart_ef994.resize();
});
