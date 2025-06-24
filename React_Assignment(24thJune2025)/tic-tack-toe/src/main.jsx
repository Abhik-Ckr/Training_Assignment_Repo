import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './components/App'
import { Provider } from 'react-redux'
import store from './reducers/store'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store}>
      <p>REACT HOMEWORK</p>
      <App />
    </Provider>
  </StrictMode>,
)
