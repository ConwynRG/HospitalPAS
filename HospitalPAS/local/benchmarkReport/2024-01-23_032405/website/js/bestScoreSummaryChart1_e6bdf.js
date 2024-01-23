
var chart_bestScoreSummaryChart1_e6bdf = new Chart(document.getElementById('chart_bestScoreSummaryChart1_e6bdf'), {
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
                    -650, -13600, -700, -2450
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    -350, -13900, -800, -1600
                  ]
                }, 
{
                  label: 'LAHC 400',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    -650, -14200, -1950, -6300
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                  grouped: true,
                    borderWidth: 4
,
                  data: [
                    -650, -13300, -700, -1600
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    -650, -14050, -800, -1850
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    -650, -14300, -800, -2750
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
                text: 'Best soft score summary (higher is better)'
            }
        },
        scales: {
            x: {
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
  chart_bestScoreSummaryChart1_e6bdf.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreSummaryChart1_e6bdf.resize();
});