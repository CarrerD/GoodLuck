import React from 'react';
import axios from "axios";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      id: 0,
      eid: '',
      name: '',
      email: '',
      place: ''
    }

  }
  componentDidMount() {
    axios.get("http://localhost:8080/api/")
      .then((res) => {
        this.setState({
          users: res.data,
          id: 0,
          eid: '',
          name: '',
          email: '',
          place: ''
        })
      })
  }
  submit(event, id) {
    event.preventDefault();
    if (id === 0) {
      axios.post("http://localhost:8080/api/", {
        eid: this.state.eid,
        name: this.state.name,
        email: this.state.email,
        place: this.state.place
      })
        .then((res) => {
          this.componentDidMount();
        })
    } else {
      axios.put("http://localhost:8080/api/", {
        id: this.state.id,
        eid: this.state.eid,
        name: this.state.name,
        email: this.state.email,
        place: this.state.place
      }).then(() => {
        this.componentDidMount();
      })

    }

  }
  delete(id) {
    axios.delete(`http://localhost:8080/api/${id}`)
      .then(() => {
        this.componentDidMount();
      })
  }
  edit(id) {
    axios.get(`http://localhost:8080/api/${id}`)
      .then((res) => {
        console.log(res.data);
        this.setState({
          id: res.data.id,
          eid: res.data.eid,
          name: res.data.name,
          email: res.data.email,
          place: res.data.place
        })
      })
  }
  render() {
    return (
      <div className="container" >

        <div className="row">
          <div className="col s5">
            <div className="center">
              <br></br>
              <img src="benutzer.png" width="150" height="150" alt="Benutzer" />
            </div>
            <form onSubmit={(e) => this.submit(e, this.state.id)}>
              <div class="input-field col s12">
                <i class="material-icons prefix">work</i>
                <input onChange={(e) => this.setState({ eid: e.target.value })} value={this.state.eid} type="text" id="autocomplete-input" class="autocomplete" />
                <label for="autocomplete-input">Personalnummer</label>
              </div>
              <div class="input-field col s12">
                <i class="material-icons prefix">person</i>
                <input onChange={(e) => this.setState({ name: e.target.value })} value={this.state.name} type="text" id="autocomplete-input" class="autocomplete" />
                <label for="autocomplete-input">Name</label>
              </div>
              <div class="input-field col s12">
                <i class="material-icons prefix">email</i>
                <input onChange={(e) => this.setState({ email: e.target.value })} value={this.state.email} type="email" id="autocomplete-input" class="autocomplete" />
                <label for="autocomplete-input">E-Mail</label>
              </div>
              <div class="input-field col s12">
                <i class="material-icons prefix">place</i>
                <input onChange={(e) => this.setState({ place: e.target.value })} value={this.state.place} type="text" id="autocomplete-input" class="autocomplete" />
                <label for="autocomplete-input">Einsatzort</label>
              </div>
              <button class="btn waves-effect waves-light right" type="submit" name="action">Absenden
                <i class="material-icons right">send</i>
              </button>
            </form>
          </div>
          <div className="col s7">
            <div className="center">
              <br></br>
              <img src="mitarbeiter.png" alt="Mitarbeiter" className="center" />
              <h5>Sogetisten</h5>
            </div>


            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>E-Mail</th>
                  <th>Einsatzort</th>
                </tr>
              </thead>

              <tbody>
                {
                  this.state.users.map(user =>
                    <tr key={user.id}>
                      <td>{user.eid}</td>
                      <td>{user.name}</td>
                      <td>{user.email}</td>
                      <td>{user.place}</td>
                     
                      <td>
                        <button onClick={(e) => this.edit(user.id)} class="btn waves-effect waves-light" type="submit" name="action">
                          <i class="material-icons">edit</i>
                        </button>
                      </td>
                      <td>
                        <button onClick={(e) => this.delete(user.id)} class="btn waves-effect red accent-4" type="submit" name="action">
                          <i class="material-icons">delete</i>
                        </button>
                      </td>
                    </tr>
                  )
                }

              </tbody>
            </table>
          </div>

        </div>
      </div>
    );
  }
}

export default App;