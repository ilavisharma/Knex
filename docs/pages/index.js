import React from 'react';
import Layout from '../components/Layout';

const Home = () => (
  <Layout>
    <h2 className="display-2 text-center">Knex-Java</h2>
    <h5 className="well text-center">
      A SQL query builder that is flexible, portable, and fun to use!
    </h5>
    <hr />
    Get up and running with a database connection in just 2 steps
    <img src="/setup.png" alt="setup instructions" className="img-fluid rounded mx-auto d-block" />
  </Layout>
);

export default Home;
