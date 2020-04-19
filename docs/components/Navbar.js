import React from 'react';
import Link from 'next/link';
import Head from './Head';

const Navbar = () => (
  <>
    <Head title="Knex" description="SQL query builder for java" />
    <nav className="navbar navbar-light bg-light">
      <Link href="/">
        <a className="navbar-brand">Knex</a>
      </Link>
    </nav>
  </>
);

export default Navbar;
