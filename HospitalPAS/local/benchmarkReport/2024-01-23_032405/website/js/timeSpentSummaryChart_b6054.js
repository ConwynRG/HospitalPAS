
var chart_timeSpentSummaryChart_b6054 = new Chart(document.getElementById('chart_timeSpentSummaryChart_b6054'), {
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
                    10921, 20906, 18516, 13671
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    10007, 17631, 14273, 34210
                  ]
                }, 
{
                  label: 'LAHC 400',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    10011, 16052, 39712, 200721
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                  grouped: true,
                    borderWidth: 4
,
                  data: [
                    10008, 30557, 11465, 17964
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    10063, 16747, 14730, 27150
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    10074, 14729, 14114, 19067
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
                tooltip: {
                    callbacks: {
                            label: function(context) {
                                let label = context.dataset.label || '';
                                return label + ": " + humanizeTime(context.parsed.y);
                            }
                    }
                },
            title: {
                display: true,
                text: 'Time spent summary (lower time is better)'
            }
        },
        scales: {
            x: {
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Time spent'
                },
                ticks: {
                        stepSize: 1000
                        ,
                        callback: function(value, index, ticks) {
                            return humanizeTime(value);
                        }
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
  chart_timeSpentSummaryChart_b6054.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_timeSpentSummaryChart_b6054.resize();
});