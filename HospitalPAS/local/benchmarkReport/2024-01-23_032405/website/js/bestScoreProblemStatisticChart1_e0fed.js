
var chart_bestScoreProblemStatisticChart1_e0fed = new Chart(document.getElementById('chart_bestScoreProblemStatisticChart1_e0fed'), {
    type: 'line',
    data: {
        labels: [
            167, 171, 177, 183, 186, 189, 191, 192, 198, 216, 236, 297, 366, 407, 437, 442, 453, 470, 533, 536, 560, 568, 593, 601, 666, 668, 678, 683, 684, 685, 696, 729, 730, 731, 746, 767, 774, 783, 784, 785, 793, 794, 806, 834, 843, 848, 855, 885, 886, 897, 906, 907, 908, 920, 922, 934, 951, 952, 965, 975, 976, 977, 978, 980, 981, 984, 991, 993, 994, 995, 996, 1002, 1009, 1019, 1027, 1029, 1031, 1038, 1053, 1091, 1092, 1093, 1102, 1105, 1106, 1129, 1130, 1156, 1181, 1192, 1193, 1206, 1230, 1231, 1254, 1255, 1258, 1260, 1269, 1271, 1283, 1284, 1287, 1288, 1292, 1294, 1301, 1306, 1308, 1367, 1368, 1374, 1375, 1377, 1378, 1379, 1397, 1407, 1420, 1421, 1465, 1479, 1480, 1555, 1559, 1618, 1627, 1629, 1636, 1637, 1645, 1649, 1663, 1664, 1668, 1684, 1714, 1732, 1755, 1756, 1759, 1760, 1770, 1771, 1805, 1827, 1835, 1887, 1906, 1924, 1937, 1944, 1967, 1993, 1994, 2052, 2075, 2077, 2078, 2106, 2124, 2139, 2140, 2168, 2176, 2177, 2185, 2187, 2196, 2216, 2225, 2239, 2266, 2267, 2284, 2339, 2348, 2402, 2460, 2493, 2525, 2533, 2548, 2573, 2588, 2618, 2644, 2666, 2675, 2700, 2705, 2733, 2736, 2825, 2849, 2871, 2890, 2891, 2928, 2959, 3014, 3027, 3028, 3040, 3048, 3068, 3070, 3072, 3074, 3085, 3099, 3115, 3168, 3196, 3219, 3244, 3331, 3336, 3351, 3354, 3364, 3373, 3398, 3411, 3475, 3512, 3565, 3588, 3671, 3680, 3711, 3713, 3766, 3798, 3803, 3857, 3862, 3888, 3903, 3958, 3974, 3993, 4013, 4020, 4044, 4064, 4106, 4107, 4130, 4219, 4238, 4259, 4267, 4313, 4316, 4438, 4441, 4457, 4523, 4539, 4558, 4616, 4692, 4698, 4736, 4833, 4865, 4883, 4904, 4914, 4929, 4943, 4950, 5035, 5037, 5041, 5087, 5264, 5344, 5360, 5363, 5419, 5424, 5429, 5466, 5467, 5488, 5524, 5541, 5562, 5611, 5648, 5742, 5751, 5790, 5814, 5828, 5855, 5926, 6007, 6049, 6104, 6225, 6229, 6232, 6250, 6288, 6339, 6457, 6497, 6519, 6562, 6587, 6607, 6859, 6860, 6932, 6935, 7044, 7322, 7397, 7454, 7474, 7483, 7696, 7744, 7765, 7777, 7868, 7904, 7916, 7964, 8141, 8261, 8381, 8401, 8468, 8538, 8560, 8640, 8646, 8663, 8684, 8827, 8845, 8920, 8967, 9058, 9067, 9168, 9207, 9610, 9744, 9829, 9943, 10133, 10307, 10380, 10512, 10566, 10701, 10727, 11049, 11360, 11395, 11432, 11775, 11846, 11877, 11900, 11919, 12317, 12327, 12607, 12709, 12809, 12880, 13101, 13671, 13703, 13727, 13785, 13939, 14048, 14054, 14069, 14200, 14313, 14346, 14353, 14426, 15364, 15793, 15865, 15974, 15984, 16073, 16174, 16316, 16458, 16557, 16941, 17085, 17141, 17144, 17150, 17426, 17427, 17452, 17964, 17975, 18050, 18130, 18151, 18179, 18180, 18530, 18811, 19067, 19673, 19830, 20090, 20181, 20265, 20309, 21043, 21118, 21353, 21621, 21622, 21862, 22144, 22392, 22609, 22647, 22713, 22978, 23012, 23684, 23814, 24126, 24198, 24210, 25089, 25149, 25352, 25793, 26286, 26289, 26661, 26671, 26918, 27008, 27150, 27465, 27499, 27881, 27933, 28166, 29146, 29628, 30366, 30614, 30683, 32023, 32248, 32403, 32589, 33069, 34196, 34210, 34511, 34516, 34749, 35678, 35679, 35680, 37638, 39626, 40914, 41027, 41501, 42140, 42740, 42742, 42819, 43686, 45140, 46061, 46508, 47096, 47221, 47531, 47888, 48586, 49034, 49339, 49947, 49948, 50040, 51624, 51849, 52397, 53023, 53024, 54268, 54466, 55431, 56278, 56944, 57907, 58714, 59072, 59147, 59263, 59679, 60319, 61239, 61601, 62351, 63497, 63898, 65550, 65970, 67231, 68313, 68630, 69591, 69994, 70733, 71423, 71836, 73311, 73946, 77409, 78181, 80362, 81139, 81331, 82700, 83782, 84603, 85710, 86241, 86737, 89141, 89359, 91472, 91568, 92281, 93273, 94844, 95912, 96824, 99800, 100662, 101304, 102501, 102536, 103247, 105027, 105607, 107554, 109865, 110920, 115298, 116593, 117087, 126721, 128989, 129552, 135927, 137308, 138108, 143176, 147924, 149752, 154450, 162624, 164850, 168017, 168476, 176927, 185127, 187394, 189095, 190721, 200721
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , -3500, , -3700, , , -3600, , , , , , , , -3500, , -3450, -3250, , , , , -3200, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2900, , , , , , , , , , , , , , , , , , , , , , , , , -2850, , , , , , , , -2800, , -2700, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2700, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2550, -2450, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2450, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , 
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , , , -3500, , -3600, , , , , , , , , , , -3500, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -3200, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -3050, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2900, -2800, , , -2750, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2750, , -3100, -2850, -2750, , , , -2600, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2550, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2400, , , , , -2300, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2200, -2100, -2050, , , -1900, -1750, , -1650, -1600, , , , , , , , , , , , , , , , , , , , , , , , , , , , -1600, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , 
                  ]
                }, 
{
                  label: 'LAHC 400',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    -3500, , , -3650, , , , , , , , , -51900, , , , , , -55450, , , -63100, , -60700, -60450, -60150, -60100, -59450, -58800, -58550, -58250, -58200, -57700, -57650, -56850, -56800, -56000, -55900, -55650, -55550, -55450, -55050, -55000, -54850, -54450, -54100, -54000, -53750, -53600, -53400, -53250, -53200, -53150, -53100, -52850, -52300, -52150, -51800, -51450, -51250, -50950, -50900, , , , , , , , , , -50600, , , -50200, , , , -49850, -49800, -49350, , , -49200, -49100, -49050, -49000, -48650, -48400, -48300, -48200, -48050, -48000, -47900, -47850, -47800, -47700, , -47450, -47200, , , , , , , -47000, , , -46850, -46650, , , , , , -46600, -46500, -46450, -46000, -45800, -45500, -45400, -45300, -45100, -44700, , , , , , , , -44650, -44450, -44400, -44350, -44250, -44200, -44100, -43750, -43650, -43600, -43550, -43500, , -43400, -43050, -42900, -42700, -42450, , -42400, -42350, -41850, , , -41750, -41700, -41550, -41500, -41300, -41200, , , , , , -41150, -41100, -40950, -40850, -40800, -40600, -40500, , -40300, -40100, -39800, -39700, , -39450, , , -39300, -38850, , , , , -38800, -38600, -38450, -38350, -38050, , , , -37900, -37800, -37750, -37700, -37550, -37500, -37250, , , , , -37100, -36900, -36450, -36300, -36150, -36050, , , , , , -35900, , -35700, , -35550, -35350, -35250, , , -35200, , -35050, -34750, -34650, , , , -34550, -34500, -34350, , -34300, -34100, , , , -34050, -33750, , -33700, -33550, -33500, , -33450, -33350, -33300, -33150, -33050, , -32850, -32800, , -32650, -32500, -32250, -32150, -31950, , , , , , -31750, -31550, , , -31400, -31350, , -31250, -31200, -31150, -31100, , -31000, -30950, , -30900, -30750, -30650, -30550, -30350, , , -30300, -30250, -30150, -29950, -29850, -29600, -29550, -29350, -29300, , , -29250, -29000, , -28950, -28900, -28800, -28750, -28700, -28550, -28500, -28150, , , -27950, -27900, , -27750, , -27700, -27600, -27550, -27500, -27350, -27250, -26900, , , , -26750, -26700, -26600, -26500, -26400, -26300, -26250, -26200, -26150, -26000, -25800, -25700, -25600, , -25550, , -25400, -25100, -24950, -24800, -24700, -24450, -24200, -24150, -24000, -23950, -23700, -23650, -23550, -23500, -23450, -23400, -23100, -23050, -23000, -22900, -22850, -22650, , -22400, -22250, -22150, -22050, -21950, -21900, , -21750, -21600, , -21500, -21400, , , -21350, -21300, -21150, , -20850, , -20800, -20750, , -20700, -20650, -20450, -20350, -20300, -20250, -20200, -20150, -20100, -20050, , -20000, -19950, -19900, , -19850, -19800, -19750, -19700, -19550, -19300, -19250, -19150, , -19100, -19050, -19000, -18900, -18850, -18750, -18650, -18550, -18500, -18450, -18350, -18200, -18050, -17850, , , , -17800, -17500, , , -17400, , , -17350, -17200, -17150, -17100, -17050, -16950, -16900, -16850, -16800, -16750, , -16650, -16550, -16400, -16350, -16300, -16200, -16100, -15950, -15800, -15750, -15700, -15550, -15500, -15400, -15250, -15150, , -15100, -15000, -14850, -14800, -14700, -14450, -14400, -14250, -14100, -14050, -13950, -13900, -13850, -13800, -13700, -13550, -13450, -13350, -13300, -13250, -13150, -13100, -13050, -13000, -12900, -12850, -12800, -12750, -12700, -12650, -12500, -12450, -12400, -12250, -12200, -12150, -12000, -11750, -11600, -11500, -11450, -11400, -11350, -11300, -11250, -11200, -11150, -11050, -11000, -10900, -10700, -10650, -10600, -10550, -10500, -10400, -10350, -10300, -10250, -10150, -10050, -9950, -9900, -9850, -9800, -9750, -9650, -9550, -9450, -9400, -9350, -9300, -9250, -9150, -9100, -9050, -9000, -8950, -8900, -8850, -8750, -8700, -8600, -8550, -8500, -8450, -8400, -8350, -8250, -8150, -8100, -8000, -7950, -7750, -7700, -7650, -7500, -7450, -7400, -7300, -7250, -7200, -7150, -7100, -7050, -6950, -6750, -6700, -6650, -6600, -6550, -6500, -6450, -6400, -6350, -6300, -6300
                  ]
                }, 
{
                  label: 'LAHC 1000 10 (favorite)',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 4
,
                  data: [
                    , , -3500, , , -3600, , , , , , , , , , , , , , -7300, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -7250, , -7100, -6950, -6900, -6850, -6700, -6650, -6500, -6400, -6300, , -6200, -6150, , , -6100, -6050, , , , -6000, -5850, -5800, -5700, , , , , , , , , , , , , , , , -5600, -5550, -5450, -5350, -5300, -5200, , -5100, -5050, , , -5000, -4900, -4850, -4750, -4700, , , , , , , , , , , -4600, -4500, -4450, -4400, , -4300, , , , , , , , , , , , , , -4250, , , , , , -4150, , , , -4050, -4000, , -3950, , , , , -3850, -3800, -3750, , -3700, , , , , , , , , , , , , -3650, , -3550, -3500, , , -3450, -3400, , , , , , , , -3350, -3300, -3250, , , , , , , , -3200, -3150, -3100, -3050, , , , , , , , , -2950, -2900, , , -2750, , , , , , , , , , , , , -2700, -2600, -2550, , , , , , , , , , , , , , , , -2450, , , , , , -2400, , , -2350, , , , , , , -2250, , , , , , -2200, , , , , , , , , -2100, , , -2050, , , , , , , -2000, , , , , , , , , , -1950, -1900, , , -1800, , , , , , , , , -1750, -1650, , , , , , , , , , , , , , -1600, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -1600, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (100hard-1100soft) 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , , , , -3500, , , -3600, , , , -3500, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -3200, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2950, , , , , , , , , , -3050, , , -3000, -3250, -3100, , , -2950, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2900, , -2800, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2750, , , , , , , , , , -2700, , , , -2450, , , , , , -2300, , , -2150, , , , , , , , , , , -1850, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -1850, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , 
                  ]
                }, 
{
                  label: 'Simulated Annealing (200hard-2000soft) 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , -3500, , , , , , -3600, , , -3500, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -3200, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -3150, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -3000, , , , , , , , , , , , , , -2900, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2900, , , , , , , , , , , , , , -2850, , -2750, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , -2750, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , 
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
                text: 'testData500 best soft score statistic'
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
                    text: 'Best soft score'
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
  chart_bestScoreProblemStatisticChart1_e0fed.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreProblemStatisticChart1_e0fed.resize();
});
