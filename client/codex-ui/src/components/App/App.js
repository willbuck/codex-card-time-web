import logo from '../../logo.svg';
import './App.css';

import Spike from '../Spike/Spike'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        Hello, Codex Players! Check back soon for cooler stuff
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
      <Spike />
    </div>
  );
}

export default App;
