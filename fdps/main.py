from flask import Flask
from scipy.stats import johnsonsb, weibull_max, alpha

app = Flask(__name__)

@app.route('/IA')
def IA():
    params = {
        'a': 0.17660521029762424,
        'b': 0.47000167001927684,
        'loc': -0.3320893232710487,
        'scale': 1442.810112951132
    }
    value = johnsonsb.rvs(**params)
    return {
        'value': value
    }


@app.route('/TB')
def TB():
    params = {
        'a': 0.08130961793269764,
        'b': 0.4715274607401107,
        'loc': -0.022438887779267225,
        'scale': 1438.4181507990957
    }
    value = johnsonsb.rvs(**params)
    return {
        'value': value
    }

@app.route('/TR')
def TR():
    params = {
        'c': 0.2297810133890134,
        'loc': 1439.0000000000002,
        'scale': 460.97927931454547
    }
    value = weibull_max.rvs(**params)
    return {
        'value': value
    }

@app.route('/PT')
def PT():
    params = {
        'a': 5.702392951080113e-08,
        'loc': 0.7766239696400189,
        'scale': 0.3345511084967139
    }
    value = alpha.rvs(**params)
    return {
        'value': value
    }

if __name__ == '__main__':
    app.run()
    app.run(host='0.0.0.0', port=5000)